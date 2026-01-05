import React, { useState } from "react";
import "./App.css";

import Pocetna from "./pages/Pocetna";
import Klijent from "./pages/Klijent";
import SportskiTeren from "./pages/SportskiTeren";
import Iznajmljivanje from "./pages/Iznajmljivanje";
import Zaposleni from "./pages/Zaposleni";
import Login from "./pages/Login";

function App() {
  const [activePage, setActivePage] = useState("pocetna");
  const [isLoggedIn, setIsLoggedIn] = useState(!!localStorage.getItem("jwtToken"));

  // ✅ Odjava
  const handleLogout = () => {
    localStorage.clear();
    sessionStorage.clear();
    setIsLoggedIn(false);
    setActivePage("pocetna");
    window.location.reload();
  };

  // ✅ Login success
  const handleLoginSuccess = (token) => {
    localStorage.setItem("jwtToken", token);
    setIsLoggedIn(true);
    setActivePage("pocetna");
  };

  // ✅ Dinamičko renderovanje stranica
  const renderPage = () => {
    switch (activePage) {
      case "zaposleni":
        return <Zaposleni />;
      case "klijent":
        return <Klijent />;
      case "teren":
        return <SportskiTeren />;
      case "iznajmljivanje":
        return <Iznajmljivanje />;
      default:
        return <Pocetna />;
    }
  };

  // ✅ Login prikaz
  if (!isLoggedIn) {
    return <Login onLoginSuccess={handleLoginSuccess} />;
  }

  // ✅ Navigacija i sadržaj
  return (
    <div>
      <nav className="navbar">
        <div className="nav-container">
          <div className="nav-left">
            <h2 className="logo">Iznajmljivanje terena</h2>
          </div>

          <div className="nav-links">
            <button
              className={`nav-btn ${activePage === "pocetna" ? "active" : ""}`}
              onClick={() => setActivePage("pocetna")}
            >
              Početna
            </button>
            <button
              className={`nav-btn ${activePage === "zaposleni" ? "active" : ""}`}
              onClick={() => setActivePage("zaposleni")}
            >
              Zaposleni
            </button>
            <button
              className={`nav-btn ${activePage === "klijent" ? "active" : ""}`}
              onClick={() => setActivePage("klijent")}
            >
              Klijenti
            </button>
            <button
              className={`nav-btn ${activePage === "teren" ? "active" : ""}`}
              onClick={() => setActivePage("teren")}
            >
              Sportski tereni
            </button>
            <button
              className={`nav-btn ${activePage === "iznajmljivanje" ? "active" : ""}`}
              onClick={() => setActivePage("iznajmljivanje")}
            >
              Iznajmljivanja
            </button>
          </div>

          <div className="nav-right">
            <button className="btn logout" onClick={handleLogout}>
              Odjavi se
            </button>
          </div>
        </div>
      </nav>

      {renderPage()}
    </div>
  );
}

export default App;
