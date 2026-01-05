import React, { useEffect, useState } from "react";
import "../css/Iznajmljivanje.css";
import http from "../api/http";

const Iznajmljivanje = () => {
  const [iznajmljivanja, setIznajmljivanja] = useState([]);
  const [tereni, setTereni] = useState([]);
  const [zaposleni, setZaposleni] = useState([]);
  const [klijenti, setKlijenti] = useState([]);
  const [recenzije, setRecenzije] = useState([]);
  const [search, setSearch] = useState("");

  const [showRecenzijaModal, setShowRecenzijaModal] = useState(false);
  const [currentRecenzijaId, setCurrentRecenzijaId] = useState(null);
  const [recenzijaForm, setRecenzijaForm] = useState({
    tekst: "",
    ocena: "",
    datum: "",
  });

  const [showAddModal, setShowAddModal] = useState(false);
  const [newIznajmljivanje, setNewIznajmljivanje] = useState({
    sportskiTerenId: "",
    zaposleniId: "",
    klijentId: "",
    datumIznajmljivanja: "",
    datumPlacanja: "",
    vremeOd: "",
    vremeDo: "",
    ukupnoSati: 2,
    ukupanIznos: "",
    nacinPlacanja: "Gotovina",
    recenzija: "",
  });

  const [cenaPoSatu, setCenaPoSatu] = useState(0);

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const [
        iznajmljivanjaRes,
        tereniRes,
        zaposleniRes,
        klijentiRes,
        recenzijeRes,
      ] = await Promise.all([
        http.get("/iznajmljivanje"),
        http.get("/sportski_teren"),
        http.get("/zaposleni"),
        http.get("/klijent"),
        http.get("/recenzija"),
      ]);

      setIznajmljivanja(iznajmljivanjaRes.data);
      setTereni(tereniRes.data);
      setZaposleni(zaposleniRes.data);
      setKlijenti(klijentiRes.data);
      setRecenzije(recenzijeRes.data);
    } catch (error) {
      console.error("❌ Greška pri učitavanju podataka:", error);
      alert("Greška pri učitavanju podataka sa servera.");
    }
  };

  const getTerenNaziv = (id) =>
    tereni.find((t) => t.idSportskiTeren === id)?.nazivTerena || "N/A";

  const getZaposleniIme = (id) => {
    const z = zaposleni.find((z) => z.idZaposleni === id);
    return z ? `${z.ime} ${z.prezime}` : "N/A";
  };

  const getKlijentIme = (id) => {
    const k = klijenti.find((k) => k.idKlijent === id);
    return k ? `${k.ime} ${k.prezime}` : "N/A";
  };

  const getRecenzija = (id) => recenzije.find((r) => r.idRecenzija === id);

  const visibleIznajmljivanja = iznajmljivanja.filter((i) => {
    const searchString = Object.values(i)
      .concat([
        getTerenNaziv(i.sportskiTerenId),
        getZaposleniIme(i.zaposleniId),
        getKlijentIme(i.klijentId),
      ])
      .join(" ")
      .toLowerCase();
    return searchString.includes(search.toLowerCase());
  });

  // RECENZIJA
  const handleAddRecenzija = (id) => {
    const today = new Date().toISOString().split("T")[0];
    setRecenzijaForm({ tekst: "", ocena: "", datum: today });
    setCurrentRecenzijaId(id);
    setShowRecenzijaModal(true);
  };

  const handleSaveRecenzija = async () => {
    if (!recenzijaForm.tekst.trim() || !recenzijaForm.ocena || !recenzijaForm.datum) {
      return alert("Popuni sva polja za recenziju!");
    }

    try {
      const recenzijaRes = await http.post("/recenzija", {
        datumRecenzije: recenzijaForm.datum,
        ocena: parseInt(recenzijaForm.ocena),
        tekst: recenzijaForm.tekst,
      });

      const recenzijaId =
        recenzijaRes.data.idRecenzija ||
        recenzijaRes.data.id ||
        null;

      if (!recenzijaId) {
        alert("Greška: nije vraćen ID recenzije!");
        return;
      }

      const izn = iznajmljivanja.find(
        (i) => i.idIznajmljivanje === currentRecenzijaId
      );

      if (!izn) {
        alert("Iznajmljivanje nije pronađeno!");
        return;
      }

      const payload = {
        datumPlacanja: izn.datumPlacanja,
        datumIznajmljivanja: izn.datumIznajmljivanja,
        vremeOd: izn.vremeOd,
        vremeDo: izn.vremeDo,
        ukupnoSati: izn.ukupnoSati,
        ukupanIznos: izn.ukupanIznos,
        nacinPlacanja: izn.nacinPlacanja,
        zaposleniId: izn.zaposleniId,
        klijentId: izn.klijentId,
        sportskiTerenId: izn.sportskiTerenId,
        recenzijaId: recenzijaId,
      };

      await http.put(`/iznajmljivanje/${currentRecenzijaId}`, payload);
      alert("✅ Recenzija uspešno sačuvana!");
      setShowRecenzijaModal(false);
      setCurrentRecenzijaId(null);
      setRecenzijaForm({ tekst: "", ocena: "", datum: "" });
      fetchData();
    } catch (error) {
      console.error("❌ Greška pri čuvanju recenzije:", error);
      alert("Greška pri čuvanju recenzije!");
    }
  };

  // DODAJ IZNAJMLJIVANJE
  const handleOpenAddModal = () => {
    setNewIznajmljivanje({
      sportskiTerenId: "",
      zaposleniId: "",
      klijentId: "",
      datumIznajmljivanja: "",
      datumPlacanja: "",
      vremeOd: "",
      vremeDo: "",
      ukupnoSati: 2,
      ukupanIznos: "",
      nacinPlacanja: "Gotovina",
      recenzija: "",
    });
    setCenaPoSatu(0);
    setShowAddModal(true);
  };

  const handleSaveNewIznajmljivanje = async () => {
    try {
      if (
        !newIznajmljivanje.sportskiTerenId ||
        !newIznajmljivanje.zaposleniId ||
        !newIznajmljivanje.klijentId ||
        !newIznajmljivanje.datumIznajmljivanja ||
        !newIznajmljivanje.datumPlacanja ||
        !newIznajmljivanje.vremeOd ||
        !newIznajmljivanje.vremeDo
      ) {
        alert("Popuni sva obavezna polja!");
        return;
      }

      const od = parseInt(newIznajmljivanje.vremeOd.substring(0, 2));
      const doV = parseInt(newIznajmljivanje.vremeDo.substring(0, 2));
      if (doV <= od) {
        alert("Vreme do mora biti posle vremena od!");
        return;
      }

      // 🟢 VALIDACIJA: zauzeće terena
      const zauzet = iznajmljivanja.some((i) => {
        if (
          i.sportskiTerenId === parseInt(newIznajmljivanje.sportskiTerenId) &&
          i.datumIznajmljivanja === newIznajmljivanje.datumIznajmljivanja
        ) {
          const postojeciOd = parseInt(i.vremeOd.substring(0, 2));
          const postojeciDo = parseInt(i.vremeDo.substring(0, 2));
          // proveravamo da li se preklapaju termini
          return od < postojeciDo && doV > postojeciOd;
        }
        return false;
      });

      if (zauzet) {
        alert("❌ Teren je već zauzet u izabranom terminu!");
        return;
      }

      const ukupnoSati = doV - od;
      const ukupanIznos = ukupnoSati * cenaPoSatu;

      const payload = {
        ...newIznajmljivanje,
        ukupnoSati,
        ukupanIznos,
        vremeOd: newIznajmljivanje.vremeOd + ":00",
        vremeDo: newIznajmljivanje.vremeDo + ":00",
      };

      await http.post("/iznajmljivanje", payload);
      await fetchData();
      setShowAddModal(false);
      alert("✅ Iznajmljivanje uspešno dodato!");
    } catch (error) {
      console.error("❌ Greška pri dodavanju iznajmljivanja:", error);
      if (error.response?.status === 403)
        alert("Nemaš dozvolu za ovu akciju (403). Proveri token.");
      else alert("Greška pri dodavanju iznajmljivanja!");
    }
  };

  const handleTerenChange = (terenId) => {
    setNewIznajmljivanje({
      ...newIznajmljivanje,
      sportskiTerenId: terenId,
    });

    const teren = tereni.find((t) => t.idSportskiTeren == terenId);
    setCenaPoSatu(teren ? teren.cenaPoSatu : 0);
  };

  const generateTimeOptions = () => {
    const times = [];
    for (let i = 8; i <= 22; i++) {
      const hour = i.toString().padStart(2, "0");
      times.push(hour);
    }
    return times;
  };

  return (
    <div className="iznajmljivanje-page">
      <h1 className="iznajmljivanje-title">Evidencija iznajmljivanja</h1>

      <div className="top-controls">
        <div className="search-box">
          <label>Pretraga:</label>
          <input
            placeholder="Pretraži po terenu, klijentu, datumu..."
            value={search}
            onChange={(e) => setSearch(e.target.value)}
          />
        </div>
        <button className="btn primary" onClick={handleOpenAddModal}>
          ➕ Dodaj iznajmljivanje
        </button>
      </div>

      {/* tabela */}
      <div className="iznajmljivanje-table-container">
        {visibleIznajmljivanja.length === 0 ? (
          <div className="empty">Nema iznajmljivanja za prikaz.</div>
        ) : (
          <table className="iznajmljivanje-table">
            <thead>
              <tr>
                <th>ID</th>
                <th>Teren</th>
                <th>Klijent</th>
                <th>Zaposleni</th>
                <th>Datum</th>
                <th>Vreme</th>
                <th>Trajanje</th>
                <th>Iznos</th>
                <th>Plaćanje</th>
                <th>Recenzija</th>
              </tr>
            </thead>
            <tbody>
              {visibleIznajmljivanja.map((i) => {
                const rec = getRecenzija(i.recenzijaId);
                return (
                  <tr key={i.idIznajmljivanje}>
                    <td>{i.idIznajmljivanje}</td>
                    <td>{getTerenNaziv(i.sportskiTerenId)}</td>
                    <td>{getKlijentIme(i.klijentId)}</td>
                    <td>{getZaposleniIme(i.zaposleniId)}</td>
                    <td>{i.datumIznajmljivanja}</td>
                    <td>
                      {i.vremeOd?.substring(0, 5)} -{" "}
                      {i.vremeDo?.substring(0, 5)}
                    </td>
                    <td>{i.ukupnoSati}h</td>
                    <td>{i.ukupanIznos} RSD</td>
                    <td>{i.nacinPlacanja}</td>
                    <td>
                      {rec ? (
                        <div className="recenzija-detalji">
                          <p><strong>{rec.ocena}/5</strong></p>
                          <small>{rec.tekst}</small>
                        </div>
                      ) : (
                        <button
                          className="btn recenzija-btn"
                          onClick={() => handleAddRecenzija(i.idIznajmljivanje)}
                        >
                          ➕ Recenzija
                        </button>
                      )}
                    </td>
                  </tr>
                );
              })}
            </tbody>
          </table>
        )}
      </div>

      {/* MODAL za dodavanje */}
      {showAddModal && (
        <div className="modal-overlay" onClick={() => setShowAddModal(false)}>
          <div className="modal" onClick={(e) => e.stopPropagation()}>
            <h2>Dodaj novo iznajmljivanje</h2>
            <form className="modal-form">
              <div className="form-grid">
                <select
                  value={newIznajmljivanje.sportskiTerenId}
                  onChange={(e) => handleTerenChange(e.target.value)}
                >
                  <option value="">Izaberi teren</option>
                  {tereni.map((t) => (
                    <option key={t.idSportskiTeren} value={t.idSportskiTeren}>
                      {t.nazivTerena}
                    </option>
                  ))}
                </select>

                <div className="teren-cena">
                  💰 Cena po satu: <strong>{cenaPoSatu || "—"} RSD</strong>
                </div>

                <select
                  value={newIznajmljivanje.klijentId}
                  onChange={(e) =>
                    setNewIznajmljivanje({
                      ...newIznajmljivanje,
                      klijentId: e.target.value,
                    })
                  }
                >
                  <option value="">Izaberi klijenta</option>
                  {klijenti.map((k) => (
                    <option key={k.idKlijent} value={k.idKlijent}>
                      {k.ime} {k.prezime}
                    </option>
                  ))}
                </select>

                <select
                  value={newIznajmljivanje.zaposleniId}
                  onChange={(e) =>
                    setNewIznajmljivanje({
                      ...newIznajmljivanje,
                      zaposleniId: e.target.value,
                    })
                  }
                >
                  <option value="">Izaberi zaposlenog</option>
                  {zaposleni.map((z) => (
                    <option key={z.idZaposleni} value={z.idZaposleni}>
                      {z.ime} {z.prezime}
                    </option>
                  ))}
                </select>

                <input
                  type="date"
                  value={newIznajmljivanje.datumIznajmljivanja}
                  onChange={(e) =>
                    setNewIznajmljivanje({
                      ...newIznajmljivanje,
                      datumIznajmljivanja: e.target.value,
                    })
                  }
                />

                <input
                  type="date"
                  value={newIznajmljivanje.datumPlacanja}
                  onChange={(e) =>
                    setNewIznajmljivanje({
                      ...newIznajmljivanje,
                      datumPlacanja: e.target.value,
                    })
                  }
                />

                <select
                  value={newIznajmljivanje.vremeOd}
                  onChange={(e) =>
                    setNewIznajmljivanje({
                      ...newIznajmljivanje,
                      vremeOd: e.target.value,
                      vremeDo: "",
                    })
                  }
                >
                  <option value="">Vreme od</option>
                  {generateTimeOptions().map((t) => (
                    <option key={t} value={t}>
                      {t}:00
                    </option>
                  ))}
                </select>

                <select
                  value={newIznajmljivanje.vremeDo}
                  onChange={(e) =>
                    setNewIznajmljivanje({
                      ...newIznajmljivanje,
                      vremeDo: e.target.value,
                    })
                  }
                >
                  <option value="">Vreme do</option>
                  {generateTimeOptions()
                    .filter((t) => !newIznajmljivanje.vremeOd || t > newIznajmljivanje.vremeOd)
                    .map((t) => (
                      <option key={t} value={t}>
                        {t}:00
                      </option>
                    ))}
                </select>

                <select
                  value={newIznajmljivanje.nacinPlacanja}
                  onChange={(e) =>
                    setNewIznajmljivanje({
                      ...newIznajmljivanje,
                      nacinPlacanja: e.target.value,
                    })
                  }
                >
                  <option value="Gotovina">Gotovina</option>
                  <option value="Kartica">Kartica</option>
                  <option value="Online">Online</option>
                </select>
              </div>

              <div className="modal-actions">
                <button
                  type="button"
                  className="btn cancel"
                  onClick={() => setShowAddModal(false)}
                >
                  Otkaži
                </button>
                <button
                  type="button"
                  className="btn save"
                  onClick={handleSaveNewIznajmljivanje}
                >
                  Sačuvaj
                </button>
              </div>
            </form>
          </div>
        </div>
      )}

      {/* MODAL: Recenzija */}
      {showRecenzijaModal && (
        <div className="modal-overlay" onClick={() => setShowRecenzijaModal(false)}>
          <div className="modal" onClick={(e) => e.stopPropagation()}>
            <h2>Dodaj Recenziju</h2>
            <div className="modal-form" style={{ display: "flex", flexDirection: "column", gap: "10px" }}>
              <textarea
                rows="4"
                placeholder="Unesi tekst recenzije..."
                value={recenzijaForm.tekst}
                onChange={(e) =>
                  setRecenzijaForm({ ...recenzijaForm, tekst: e.target.value })
                }
              />
              <input
                type="number"
                min="1"
                max="5"
                placeholder="Ocena (1-5)"
                value={recenzijaForm.ocena}
                onChange={(e) =>
                  setRecenzijaForm({ ...recenzijaForm, ocena: e.target.value })
                }
              />
              <input
                type="date"
                value={recenzijaForm.datum}
                onChange={(e) =>
                  setRecenzijaForm({ ...recenzijaForm, datum: e.target.value })
                }
              />
              <div className="modal-actions">
                <button className="btn cancel" onClick={() => setShowRecenzijaModal(false)}>
                  Otkaži
                </button>
                <button className="btn save" onClick={handleSaveRecenzija}>
                  Sačuvaj
                </button>
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default Iznajmljivanje;
