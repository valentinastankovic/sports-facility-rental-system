import React, { useEffect, useState } from "react";
import "../css/SportskiTeren.css";
import http from "../api/http";
import fudbalImg from "../pictures/fudbalIcon.png";
import kosarkaImg from "../pictures/basketballIcon.png";
import odbojkaImg from "../pictures/voleyballIcon.png";
import tenisImg from "../pictures/tenisballIcon.png";
import rukometImg from "../pictures/handballIcon.png";

const TIP_SLIKE = {
  odbojka: odbojkaImg,
  kosarka: kosarkaImg,
  rukomet: rukometImg,
  fudbal: fudbalImg,
  tenis: tenisImg,
};

const SportskiTeren = () => {
  const [tereni, setTereni] = useState([]);
  const [tipovi, setTipovi] = useState([]);
  const [selectedType, setSelectedType] = useState(null);
  const [search, setSearch] = useState("");
  const [showModal, setShowModal] = useState(false);
  const [editingTeren, setEditingTeren] = useState(null);
  const [formData, setFormData] = useState({
    nazivTerena: "",
    lokacija: "",
    cenaPoSatu: "",
    idTipTerena: "",
  });

  const [showRecenzijeModal, setShowRecenzijeModal] = useState(false);
  const [recenzije, setRecenzije] = useState([]);
  const [trenutniTeren, setTrenutniTeren] = useState(null);

  useEffect(() => {
    fetchTereni();
    fetchTipoviTerena();
  }, []);

  const fetchTipoviTerena = async () => {
    try {
      const response = await http.get("/tip_terena");
      const mappedTipovi = response.data.map((tip) => {
        const code = String(tip.tip).toLowerCase();
        return {
          id: tip.idTipTerena,
          code: code,
          label: tip.tip,
          opis: tip.opis,
          img: TIP_SLIKE[code] || null,
          ...tip,
        };
      });
      setTipovi(mappedTipovi);
    } catch (error) {
      console.error("❌ Greška pri učitavanju tipova terena:", error);
      alert("Greška pri učitavanju tipova terena sa servera");
    }
  };

  const fetchTereni = async () => {
    try {
      const response = await http.get("/sportski_teren");
      setTereni(response.data);
    } catch (error) {
      console.error("❌ Greška pri učitavanju terena:", error);
      alert("Greška pri učitavanju sa servera");
    }
  };

  const visibleTereni = tereni
    .filter((t) => {
      const tipId =
        t.tipTerena?.idTipTerena ||
        t.idTipTerena ||
        t.tipTerenaId ||
        t.tip_terena_id;
      return selectedType ? tipId === selectedType : true;
    })
    .filter((t) =>
      Object.values(t)
        .join(" ")
        .toLowerCase()
        .includes(search.toLowerCase())
    );

  const handleOpenModal = (teren = null) => {
    if (teren) {
      const tipId =
        teren.tipTerena?.idTipTerena ||
        teren.idTipTerena ||
        teren.tipTerenaId ||
        teren.tip_terena_id;
      setEditingTeren(teren);
      setFormData({
        nazivTerena: teren.nazivTerena,
        lokacija: teren.lokacija,
        cenaPoSatu: String(teren.cenaPoSatu),
        idTipTerena: tipId || "",
      });
    } else {
      setEditingTeren(null);
      setFormData({
        nazivTerena: "",
        lokacija: "",
        cenaPoSatu: "",
        idTipTerena: selectedType || "",
      });
    }
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setShowModal(false);
    setEditingTeren(null);
  };

  const handleSave = async () => {
    if (
      !formData.nazivTerena ||
      !formData.idTipTerena ||
      !formData.cenaPoSatu ||
      !formData.lokacija
    ) {
      return alert("Popuni sva polja!");
    }

    // ✅ Ispravljena provera postojanja terena (ne gleda teren koji se menja)
    const existingTeren = tereni.find((t) => {
      if (editingTeren && t.idSportskiTeren === editingTeren.idSportskiTeren) {
        return false;
      }
      return (
        t.nazivTerena.trim().toLowerCase() ===
        formData.nazivTerena.trim().toLowerCase()
      );
    });

    if (existingTeren) {
      alert("⚠️ Sportski teren sa ovim nazivom već postoji!");
      return;
    }

    try {
      const payload = {
        nazivTerena: formData.nazivTerena,
        lokacija: formData.lokacija,
        cenaPoSatu: parseFloat(formData.cenaPoSatu),
        tipTerenaId: formData.idTipTerena,
      };

      if (editingTeren) {
        await http.put(`/sportski_teren/${editingTeren.idSportskiTeren}`, payload);
      } else {
        await http.post("/sportski_teren", payload);
      }

      alert("✅ Sistem je zapamtio sportski teren.");
      fetchTereni();
      handleCloseModal();
    } catch (error) {
      console.error("❌ Greška pri čuvanju:", error);
      alert("❌ Sistem ne može da zapamti sportski teren.");
    }
  };

  const handleDelete = async (id) => {
    if (window.confirm("Da li želiš da obrišeš ovaj teren?")) {
      try {
        const iznajmljivanjaRes = await http.get("/iznajmljivanje");
        const iznajmljivanja = iznajmljivanjaRes.data;
        const postojiZakazano = iznajmljivanja.some(
          (iz) => iz.sportskiTerenId === id
        );

        if (postojiZakazano) {
          alert(
            "❌ Sportski teren ne može da se obriše jer već ima zakazano iznajmljivanje."
          );
          return;
        }

        await http.delete(`/sportski_teren/${id}`);
        setTereni((prev) => prev.filter((t) => t.idSportskiTeren !== id));
      } catch (error) {
        console.error("❌ Greška pri brisanju:", error);
        alert("Greška pri brisanju terena.");
      }
    }
  };

  const handleShowRecenzije = async (teren) => {
    try {
      setTrenutniTeren(teren);
      setRecenzije([]);
      setShowRecenzijeModal(true);

      const iznajmljivanjaRes = await http.get("/iznajmljivanje");
      const iznajmljivanja = iznajmljivanjaRes.data;

      const relevantna = iznajmljivanja.filter(
        (iz) =>
          iz.sportskiTerenId === teren.idSportskiTeren && iz.recenzijaId != null
      );

      if (relevantna.length === 0) {
        setRecenzije([]);
        return;
      }

      const recenzijePromises = relevantna.map((iz) =>
        http.get(`/recenzija/${iz.recenzijaId}`)
      );
      const recenzijeResponses = await Promise.all(recenzijePromises);

      setRecenzije(recenzijeResponses.map((r) => r.data));
    } catch (error) {
      console.error("❌ Greška pri učitavanju recenzija:", error);
      alert("Greška pri učitavanju recenzija za ovaj teren.");
    }
  };

  const getTipLabel = (teren) => {
    const id =
      teren.tipTerena?.idTipTerena ||
      teren.idTipTerena ||
      teren.tipTerenaId ||
      teren.tip_terena_id;

    const tipNaziv =
      teren.tipTerena?.tip ||
      teren.tipTerena?.naziv ||
      teren.tipTerena?.ime ||
      teren.tipTerena?.code;

    const normalizedTip = String(tipNaziv || "").toLowerCase();

    return (
      tipovi.find((x) => x.id === id)?.label ||
      tipovi.find((x) => x.code.toLowerCase() === normalizedTip)?.label ||
      "Nepoznat tip"
    );
  };

  const getOpisTipa = (idTipTerena) => {
    const tip = tipovi.find((t) => t.id === parseInt(idTipTerena));
    return tip ? tip.opis : "";
  };

  return (
    <div className="teren-page">
      <h1 className="teren-title">
        {selectedType
          ? `Tereni za ${getTipLabel({
              tipTerena: { idTipTerena: selectedType },
            })}`
          : "Svi sportski tereni"}
      </h1>

      <div className="tipovi-grid">
        {tipovi.map((t) => (
          <div
            key={t.id}
            className={`tip-card ${selectedType === t.id ? "active" : ""}`}
            onClick={() => setSelectedType(t.id)}
          >
            <div className="tip-icon">
              {t.img ? <img src={t.img} alt={t.label} /> : <span>⚽</span>}
            </div>
            <div className="tip-label">{t.label}</div>
          </div>
        ))}
        <div
          className={`tip-card ${selectedType === null ? "active" : ""}`}
          onClick={() => setSelectedType(null)}
        >
          <div className="tip-icon">
            <span>★</span>
          </div>
          <div className="tip-label">Svi</div>
        </div>
      </div>

      <div className="top-controls">
        <div className="search-box">
          <label>Pretraga:</label>
          <input
            placeholder="Unesi bilo koji podatak..."
            value={search}
            onChange={(e) => setSearch(e.target.value)}
          />
        </div>
        <div className="controls-right">
          <button className="btn primary" onClick={() => handleOpenModal()}>
            ➕ Dodaj teren
          </button>
        </div>
      </div>

      {search.trim() !== "" && (
        <p className="search-message">
          {visibleTereni.length > 0
            ? "✅ Sistem je našao sportske terene po zadatim kriterijumima."
            : "❌ Sistem ne može da nađe sportske terene po zadatim kriterijumima."}
        </p>
      )}

      <div className="teren-list">
        {visibleTereni.length === 0 ? (
          <div className="empty">Nema terena za prikaz.</div>
        ) : (
          visibleTereni.map((t) => (
            <div className="teren-card" key={t.idSportskiTeren}>
              <div className="teren-content">
                <h3>{t.nazivTerena}</h3>
                <p>📍 {t.lokacija}</p>
                <p>💰 {t.cenaPoSatu} RSD/h</p>
                <p>Tip: {getTipLabel(t)}</p>
                <div className="actions">
                  <button
                    className="btn small edit"
                    onClick={() => handleOpenModal(t)}
                  >
                    Izmeni
                  </button>
                  <button
                    className="btn small delete"
                    onClick={() => handleDelete(t.idSportskiTeren)}
                  >
                    Obriši
                  </button>
                  <button
                    className="btn small info"
                    onClick={() => handleShowRecenzije(t)}
                  >
                    Recenzije
                  </button>
                </div>
              </div>
            </div>
          ))
        )}
      </div>

      {/* Modal za dodavanje/izmenu */}
      {showModal && (
        <div className="modal-overlay" onClick={handleCloseModal}>
          <div className="modal" onClick={(e) => e.stopPropagation()}>
            <h2>{editingTeren ? "Izmeni teren" : "Dodaj teren"}</h2>

            <div className="modal-form">
              <input
                type="text"
                placeholder="Naziv terena"
                value={formData.nazivTerena}
                onChange={(e) =>
                  setFormData({ ...formData, nazivTerena: e.target.value })
                }
              />
              <input
                type="text"
                placeholder="Lokacija"
                value={formData.lokacija}
                onChange={(e) =>
                  setFormData({ ...formData, lokacija: e.target.value })
                }
              />
              <input
                type="number"
                placeholder="Cena po satu (RSD)"
                value={formData.cenaPoSatu}
                onChange={(e) =>
                  setFormData({ ...formData, cenaPoSatu: e.target.value })
                }
              />
              <select
                value={formData.idTipTerena}
                onChange={(e) =>
                  setFormData({
                    ...formData,
                    idTipTerena: parseInt(e.target.value) || "",
                  })
                }
              >
                <option value="">-- Izaberi tip terena --</option>
                {tipovi.map((tp) => (
                  <option key={tp.id} value={tp.id}>
                    {tp.label}
                  </option>
                ))}
              </select>

              {formData.idTipTerena && (
                <p className="tip-opis">
                  <strong>Opis:</strong> {getOpisTipa(formData.idTipTerena)}
                </p>
              )}
            </div>

            <div className="modal-actions">
              <button className="btn cancel" onClick={handleCloseModal}>
                Otkaži
              </button>
              <button className="btn save" onClick={handleSave}>
                Sačuvaj
              </button>
            </div>
          </div>
        </div>
      )}

      {/* Modal za recenzije */}
      {showRecenzijeModal && (
        <div
          className="modal-overlay"
          onClick={() => setShowRecenzijeModal(false)}
        >
          <div className="modal" onClick={(e) => e.stopPropagation()}>
            <h2>
              Recenzije za teren:{" "}
              <span style={{ color: "#007bff" }}>
                {trenutniTeren?.nazivTerena}
              </span>
            </h2>

            {recenzije.length === 0 ? (
              <p>Nema recenzija za ovaj teren.</p>
            ) : (
              <div className="recenzije-list">
                {recenzije.map((r, index) => (
                  <React.Fragment key={r.idRecenzija}>
                    <div className="recenzija-card">
                      <p>
                        <strong>Datum:</strong> {r.datumRecenzije}
                      </p>
                      <p>
                        <strong>Ocena:</strong> {r.ocena}⭐
                      </p>
                      <p>
                        <strong>Komentar:</strong> {r.tekst}
                      </p>
                    </div>
                    {index !== recenzije.length - 1 && (
                      <hr className="recenzija-separator" />
                    )}
                  </React.Fragment>
                ))}
              </div>
            )}

            <div className="modal-actions">
              <button
                className="btn cancel"
                onClick={() => setShowRecenzijeModal(false)}
              >
                Zatvori
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default SportskiTeren;
