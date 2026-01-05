import React, { useEffect, useState } from "react";
import "../css/Zaposleni.css";

const Zaposleni = () => {
  const [zaposleni, setZaposleni] = useState([]);
  const [iznajmljivanja, setIznajmljivanja] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [modalOpen, setModalOpen] = useState(false);
  const [editingZaposleni, setEditingZaposleni] = useState(null);
  const [formData, setFormData] = useState({
    ime: "",
    prezime: "",
    email: "",
    username: "",
    password: "",
    uloga: "ZAPOSLENI",
  });

  const apiUrl = "http://localhost:8080/api/zaposleni";
  const iznajmljivanjeUrl = "http://localhost:8080/api/iznajmljivanje";

  const loggedInUser = localStorage.getItem("ime");

  useEffect(() => {
    fetchZaposleni();
    fetchIznajmljivanja();
  }, []);

  const fetchZaposleni = async () => {
    setLoading(true);
    setError(null);
    try {
      const res = await fetch(apiUrl);
      if (!res.ok) throw new Error(`Server returned ${res.status}`);
      const data = await res.json();
      setZaposleni(Array.isArray(data) ? data : [data]);
    } catch (err) {
      console.error("Fetch error:", err);
      setError("Greška prilikom učitavanja zaposlenih. Proveri backend server.");
    } finally {
      setLoading(false);
    }
  };

  const fetchIznajmljivanja = async () => {
    try {
      const res = await fetch(iznajmljivanjeUrl);
      if (!res.ok) throw new Error("Greška prilikom učitavanja iznajmljivanja.");
      const data = await res.json();
      setIznajmljivanja(data);
    } catch (err) {
      console.error("Greška kod učitavanja iznajmljivanja:", err);
    }
  };

  const handleInputChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const openAddModal = () => {
    setEditingZaposleni(null);
    setFormData({
      ime: "",
      prezime: "",
      email: "",
      username: "",
      password: "",
      uloga: "ZAPOSLENI",
    });
    setModalOpen(true);
  };

  const openEditModal = (z) => {
    setEditingZaposleni(z.idZaposleni);
    setFormData({
      ime: z.ime,
      prezime: z.prezime,
      email: z.email,
      username: z.username,
      password: "",
      uloga: z.uloga,
    });
    setModalOpen(true);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Validacija da su sva polja popunjena
    if (!formData.ime || !formData.prezime || !formData.email || !formData.username || (!formData.password && !editingZaposleni)) {
      alert("❌ Popuni sva obavezna polja!");
      return;
    }

    try {
      // Validacija: email jedinstven
      const postojiEmail = zaposleni.some(
        (z) =>
          z.email.toLowerCase() === formData.email.toLowerCase() &&
          z.idZaposleni !== editingZaposleni
      );
      if (postojiEmail) {
        alert("❌ Već postoji zaposleni sa ovim emailom!");
        return;
      }

      // Validacija: username jedinstven
      const postojiUsername = zaposleni.some(
        (z) =>
          z.username.toLowerCase() === formData.username.toLowerCase() &&
          z.idZaposleni !== editingZaposleni
      );
      if (postojiUsername) {
        alert("❌ Već postoji zaposleni sa ovim korisničkim imenom!");
        return;
      }

      // Ako je dodavanje, proveri da li zaposleni već ima iznajmljivanje
      if (!editingZaposleni) {
        const zauzet = iznajmljivanja.some(
          (i) =>
            i.zaposleni &&
            i.zaposleni.email.toLowerCase() === formData.email.toLowerCase()
        );
        if (zauzet) {
          alert("❌ Ovaj zaposleni već ima zakazano iznajmljivanje i ne može se ponovo dodati!");
          return;
        }
      }

      // Slanje zahteva backendu
      const res = await fetch(
        editingZaposleni ? `${apiUrl}/${editingZaposleni}` : apiUrl,
        {
          method: editingZaposleni ? "PUT" : "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(formData),
        }
      );

      if (!res.ok) throw new Error("Greška pri čuvanju zaposlenog.");

      await fetchZaposleni();
      setModalOpen(false);
    } catch (err) {
      alert(err.message);
    }
  };

  const handleDelete = async (id) => {
    // Provera: zaposleni ima iznajmljivanje
    const imaIznajmljivanje = iznajmljivanja.some(
      (i) => i.zaposleni && i.zaposleni.idZaposleni === id
    );

    if (imaIznajmljivanje) {
      alert("❌ Ne možete obrisati zaposlenog jer već ima zakazano iznajmljivanje!");
      return; // prekida dalje izvršavanje
    }

    if (!window.confirm("Da li ste sigurni da želite da obrišete zaposlenog?"))
      return;

    try {
      const res = await fetch(`${apiUrl}/${id}`, { method: "DELETE" });
      if (!res.ok) throw new Error("❌ Ne možete obrisati zaposlenog jer već ima zakazano iznajmljivanje!");
      await fetchZaposleni();
    } catch (err) {
      alert(err.message);
    }
  };

  if (loading) return <p>Učitavanje zaposlenih...</p>;
  if (error) return <p className="error">{error}</p>;

  return (
    <div className="zaposleni-container">
      <h2>Zaposleni</h2>

      <button className="btn add" onClick={openAddModal}>
        ➕ Dodaj zaposlenog
      </button>

      <table className="zaposleni-table">
        <thead>
          <tr>
            <th>Ime</th>
            <th>Prezime</th>
            <th>Email</th>
            <th>Korisničko ime</th>
            <th>Uloga</th>
            <th>Akcije</th>
          </tr>
        </thead>
        <tbody>
          {zaposleni.length > 0 ? (
            zaposleni.map((z) => {
              const imePrezime = `${z.ime}`.trim();
              const jeUlogovani =
                imePrezime.toLowerCase() === (loggedInUser || "").toLowerCase();

              return (
                <tr key={z.idZaposleni}>
                  <td>{z.ime}</td>
                  <td>{z.prezime}</td>
                  <td>{z.email}</td>
                  <td>{z.username}</td>
                  <td>{z.uloga}</td>
                  <td>
                    <button className="btn edit" onClick={() => openEditModal(z)}>
                      Izmeni
                    </button>
                    {!jeUlogovani && (
                      <button
                        className="btn delete"
                        onClick={() => handleDelete(z.idZaposleni)}
                      >
                        Obriši
                      </button>
                    )}
                  </td>
                </tr>
              );
            })
          ) : (
            <tr>
              <td colSpan="6">Nema zaposlenih.</td>
            </tr>
          )}
        </tbody>
      </table>

      {modalOpen && (
        <div className="modal-overlay">
          <div className="modal">
            <h2>{editingZaposleni ? "Izmeni zaposlenog" : "Dodaj zaposlenog"}</h2>
            <form className="modal-form" onSubmit={handleSubmit}>
              <input
                type="text"
                name="ime"
                placeholder="Ime"
                value={formData.ime}
                onChange={handleInputChange}
                required
              />
              <input
                type="text"
                name="prezime"
                placeholder="Prezime"
                value={formData.prezime}
                onChange={handleInputChange}
                required
              />
              <input
                type="email"
                name="email"
                placeholder="Email"
                value={formData.email}
                onChange={handleInputChange}
                required
              />
              <input
                type="text"
                name="username"
                placeholder="Korisničko ime"
                value={formData.username}
                onChange={handleInputChange}
                required
              />
              <input
                type="password"
                name="password"
                placeholder="Lozinka"
                value={formData.password}
                onChange={handleInputChange}
                required={!editingZaposleni}
              />

              <div className="modal-actions">
                <button
                  type="button"
                  className="btn cancel"
                  onClick={() => setModalOpen(false)}
                >
                  Otkaži
                </button>
                <button type="submit" className="btn save">
                  {editingZaposleni ? "Sačuvaj promene" : "Dodaj"}
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
};

export default Zaposleni;
