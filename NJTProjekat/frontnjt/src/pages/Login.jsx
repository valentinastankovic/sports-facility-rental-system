import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../css/Login.css";
import "../App.css";

export default function Login({ onLoginSuccess }) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(null);
    setLoading(true);

    try {
      const res = await fetch("http://localhost:8080/api/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password }),
      });

      if (!res.ok) {
        alert("Korisničko ime i/ili lozinka nisu ispravni.");
        throw new Error("Korisničko ime i/ili lozinka nisu ispravni.");
      }

      const data = await res.json();

      const extractedName = username.replace(/\d+/g, "");
      const displayName =
        extractedName.charAt(0).toUpperCase() + extractedName.slice(1);

      localStorage.setItem("token", data.token);
      localStorage.setItem("ime", displayName);

      onLoginSuccess && onLoginSuccess(data.token);

      alert("Uspešno ste se ulogovali.");

      navigate("/", { replace: true });
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="login-root">
      <form className="login-card" onSubmit={handleSubmit}>
        <h2 className="logo">Prijava</h2>

        <label className="label">Korisničko ime</label>
        <input
          className="input"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          placeholder="Unesi korisničko ime"
          required
        />

        <label className="label">Lozinka</label>
        <input
          className="input"
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          placeholder="Unesi lozinku"
          required
        />

        {error && <div className="error">{error}</div>}

        <button className="btn" type="submit" disabled={loading}>
          {loading ? "Učitavanje..." : "Uloguj se"}
        </button>
      </form>
    </div>
  );
}
