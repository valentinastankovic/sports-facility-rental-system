import React, { useEffect, useState } from "react";
import "../css/Klijent.css";
import http from "../api/http";

function Klijent() {
  const [search, setSearch] = useState("");
  const [showModal, setShowModal] = useState(false);
  const [editingClient, setEditingClient] = useState(null);
  const [clients, setClients] = useState([]);
  const [mesta, setMesta] = useState([]);
  const [formData, setFormData] = useState({
    ime: "",
    prezime: "",
    broj_telefona: "",
    broj_licne_karte: "",
    email: "",
    mesto_id: "",
  });

  const token = localStorage.getItem("token");

  useEffect(() => {
    if (token) {
      fetchClients();
      fetchMesta();
    } else {
      alert("Niste prijavljeni. Molimo vas da se ponovo ulogujete.");
    }
  }, []);

  const fetchClients = async () => {
    try {
      const response = await http.get("/klijent", {
        headers: { Authorization: `Bearer ${token}` },
      });
      setClients(response.data);
    } catch (error) {
      console.error("❌ Greška pri učitavanju klijenata:", error);
      alert("Greška pri učitavanju klijenata sa servera. Proveri token.");
    }
  };

  const fetchMesta = async () => {
    try {
      const response = await http.get("/mesto", {
        headers: { Authorization: `Bearer ${token}` },
      });
      setMesta(response.data);
    } catch (error) {
      console.error("❌ Greška pri učitavanju mesta:", error);
    }
  };

  const filteredClients = clients.filter((c) =>
    Object.values(c).join(" ").toLowerCase().includes(search.toLowerCase())
  );

  const getMestoNaziv = (id) => {
    const mesto = mesta.find((m) => m.idMesto === id);
    return mesto ? mesto.naziv : "Nepoznato mesto";
  };

  // ✅ ISPRAVLJENA FUNKCIJA
  const handleDelete = async (idKlijent) => {
    if (!window.confirm("Da li ste sigurni da želite da obrišete klijenta?")) return;

    try {
      // Preuzmi sva iznajmljivanja
      const response = await http.get(`/iznajmljivanje`, {
        headers: { Authorization: `Bearer ${token}` },
      });

      const rentals = Array.isArray(response.data) ? response.data : [];
      console.log("🔍 Sva iznajmljivanja:", rentals);

      // Filtriraj samo ona iznajmljivanja koja pripadaju klijentu
      const klijentovaIznajmljivanja = rentals.filter(
        (i) =>
          i.klijent?.idKlijent === idKlijent ||
          i.klijentId === idKlijent ||
          i.klijent_id === idKlijent
      );

      console.log("➡️ Iznajmljivanja za klijenta:", klijentovaIznajmljivanja);

      // današnji dan bez vremena
      const today = new Date();
      today.setHours(0, 0, 0, 0);

      // filtriraj buduća iznajmljivanja
      const futureRentals = klijentovaIznajmljivanja.filter((i) => {
        if (!i.datumIznajmljivanja) return false;
        const rentalDate = new Date(i.datumIznajmljivanja);
        rentalDate.setHours(0, 0, 0, 0);
        return rentalDate >= today;
      });

      if (futureRentals.length > 0) {
        alert("❌ Ne možete obrisati klijenta jer ima zakazano iznajmljivanje.");
        return;
      }

      // ako nema zakazanih iznajmljivanja — briši
      await http.delete(`/klijent/${idKlijent}`, {
        headers: { Authorization: `Bearer ${token}` },
      });

      setClients((prev) => prev.filter((c) => c.idKlijent !== idKlijent));
      alert("✅ Klijent uspešno obrisan!");
    } catch (error) {
      console.error("❌ Greška pri brisanju klijenta:", error);
      alert("❌ Ne možete obrisati klijenta jer ima zakazano iznajmljivanje.");
    }
  };

  const handleOpenModal = (client = null) => {
    setEditingClient(client);
    if (client) {
      setFormData({
        ime: client.ime || "",
        prezime: client.prezime || "",
        broj_telefona: client.broj_telefona || "",
        broj_licne_karte: client.broj_licne_karte || "",
        email: client.email || "",
        mesto_id: client.mesto?.idMesto || client.mesto_id || "",
      });
    } else {
      setFormData({
        ime: "",
        prezime: "",
        broj_telefona: "",
        broj_licne_karte: "",
        email: "",
        mesto_id: "",
      });
    }
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setShowModal(false);
    setEditingClient(null);
  };

  const handleSave = async () => {
    const { ime, prezime, broj_telefona, broj_licne_karte, email, mesto_id } = formData;

    if (!ime || !prezime || !broj_telefona || !broj_licne_karte || !email || !mesto_id) {
      alert("⚠️ Sva polja moraju biti popunjena!");
      return;
    }

    const phoneRegex = /^06\d{7,8}$/;
    if (!phoneRegex.test(broj_telefona)) {
      alert("⚠️ Neispravan format broja telefona!");
      return;
    }

    const licnaRegex = /^\d{9}$/;
    if (!licnaRegex.test(broj_licne_karte)) {
      alert("⚠️ Broj lične karte mora imati tačno 9 cifara!");
      return;
    }

    const existingPhone = clients.find(
      (c) => c.broj_telefona === broj_telefona && c.idKlijent !== editingClient?.idKlijent
    );
    if (existingPhone) {
      alert("⚠️ Klijent sa unetim brojem telefona već postoji!");
      return;
    }

    const existingEmail = clients.find(
      (c) => c.email === email && c.idKlijent !== editingClient?.idKlijent
    );
    if (existingEmail) {
      alert("⚠️ Klijent sa unetim emailom već postoji!");
      return;
    }

    const existingBrojLicne = clients.find(
      (c) =>
        c.broj_licne_karte === broj_licne_karte &&
        c.idKlijent !== editingClient?.idKlijent
    );
    if (existingBrojLicne) {
      alert("⚠️ Klijent sa unetim brojem lične karte već postoji!");
      return;
    }

    try {
      const payload = {
        ime,
        prezime,
        broj_telefona,
        broj_licne_karte,
        email,
        mesto_id: parseInt(mesto_id),
      };

      if (editingClient) {
        await http.put(`/klijent/${editingClient.idKlijent}`, payload, {
          headers: { Authorization: `Bearer ${token}` },
        });
      } else {
        await http.post("/klijent", payload, {
          headers: { Authorization: `Bearer ${token}` },
        });
      }

      alert("✅ Sistem je zapamtio klijenta.");
      fetchClients();
      handleCloseModal();
    } catch (error) {
      console.error("❌ Greška pri čuvanju klijenta:", error);
      alert("❌ Sistem ne može da zapamti klijenta.");
    }
  };

  return (
    <div className="klijent-container">
      <h1 className="title">Lista klijenata</h1>

      <div className="top-controls">
        <div className="search-section">
          <label>Pretraga:</label>
          <input
            type="text"
            placeholder="Unesi ime, prezime, broj telefona..."
            value={search}
            onChange={(e) => setSearch(e.target.value)}
          />
        </div>
        <div className="controls-right">
          <button className="btn primary" onClick={() => handleOpenModal()}>
            ➕ Dodaj klijenta
          </button>
        </div>
      </div>

      <table className="client-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Ime</th>
            <th>Prezime</th>
            <th>Broj telefona</th>
            <th>Broj lične karte</th>
            <th>Email</th>
            <th>Mesto</th>
            <th>Akcije</th>
          </tr>
        </thead>
        <tbody>
          {filteredClients.length === 0 ? (
            <tr>
              <td colSpan="8" style={{ textAlign: "center", padding: "20px" }}>
                Nema klijenata za prikaz.
              </td>
            </tr>
          ) : (
            filteredClients.map((client) => (
              <tr key={client.idKlijent}>
                <td>{client.idKlijent}</td>
                <td>{client.ime}</td>
                <td>{client.prezime}</td>
                <td>{client.broj_telefona}</td>
                <td>{client.broj_licne_karte}</td>
                <td>{client.email}</td>
                <td>{getMestoNaziv(client.mesto_id || client.mesto?.idMesto)}</td>
                <td className="actions">
                  <button
                    className="btn small edit"
                    onClick={() => handleOpenModal(client)}
                  >
                    Izmeni
                  </button>
                  <button
                    className="btn small delete"
                    onClick={() => handleDelete(client.idKlijent)}
                  >
                    Obriši
                  </button>
                </td>
              </tr>
            ))
          )}
        </tbody>
      </table>

      {showModal && (
        <div className="modal-overlay" onClick={handleCloseModal}>
          <div className="modal" onClick={(e) => e.stopPropagation()}>
            <h2>{editingClient ? "Izmeni klijenta" : "Dodaj klijenta"}</h2>
            <div className="modal-form">
              <input
                type="text"
                placeholder="Ime"
                value={formData.ime}
                onChange={(e) =>
                  setFormData({ ...formData, ime: e.target.value })
                }
              />
              <input
                type="text"
                placeholder="Prezime"
                value={formData.prezime}
                onChange={(e) =>
                  setFormData({ ...formData, prezime: e.target.value })
                }
              />
              <input
                type="text"
                placeholder="Broj telefona (npr. 061234567)"
                value={formData.broj_telefona}
                onChange={(e) =>
                  setFormData({ ...formData, broj_telefona: e.target.value })
                }
              />
              <input
                type="text"
                placeholder="Broj lične karte (9 cifara)"
                value={formData.broj_licne_karte}
                onChange={(e) =>
                  setFormData({
                    ...formData,
                    broj_licne_karte: e.target.value,
                  })
                }
              />
              <input
                type="email"
                placeholder="Email"
                value={formData.email}
                onChange={(e) =>
                  setFormData({ ...formData, email: e.target.value })
                }
              />
              <select
                value={formData.mesto_id}
                onChange={(e) =>
                  setFormData({ ...formData, mesto_id: e.target.value })
                }
              >
                <option value="">-- Izaberi mesto --</option>
                {mesta.map((m) => (
                  <option key={m.idMesto} value={m.idMesto}>
                    {m.naziv}
                  </option>
                ))}
              </select>
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
    </div>
  );
}

export default Klijent;
