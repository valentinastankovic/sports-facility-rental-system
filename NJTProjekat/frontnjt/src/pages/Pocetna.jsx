import React, { useEffect, useState } from "react";
import "../css/Pocetna.css";
import dashboardImg from "../pictures/logoMP.png";

const Pocetna = () => {
  const [vreme, setVreme] = useState("");
  const [ime, setIme] = useState("");

  useEffect(() => {
    const updateTime = () => {
      const now = new Date();
      const sati = now.getHours().toString().padStart(2, "0");
      const minuti = now.getMinutes().toString().padStart(2, "0");
      setVreme(`${sati}:${minuti}`);
    };
    updateTime();
    const interval = setInterval(updateTime, 1000 * 60);
    return () => clearInterval(interval);
  }, []);

  // Učitaj ime iz localStorage
  useEffect(() => {
    const storedIme = localStorage.getItem("ime");
    if (storedIme) {
      setIme(storedIme);
    } else {
      setIme("zaposleni"); // fallback ako nema imena
    }
  }, []);

  const datum = new Date().toLocaleDateString("sr-RS", {
    day: "2-digit",
    month: "2-digit",
    year: "numeric",
  });

  const temperatura = "15°C";

  return (
    <div>
      <section className="dashboard-hero">
        <div className="dashboard-container">
          <div className="dashboard-text">
            <h1>Dobrodošli nazad, {ime}!</h1>
            <p>
              Pregledajte ključne podatke o klijentima, iznajmljivanjima i
              raspoloživim terenima.
            </p>

            <div className="employee-stats">
              <div className="stat-card">
                <strong>{datum}</strong>
                <span>Današnji datum</span>
              </div>
              <div className="stat-card">
                <strong>{vreme}</strong>
                <span>Trenutno vreme</span>
              </div>
              <div className="stat-card">
                <strong>{temperatura}</strong>
                <span>Temperatura</span>
              </div>
            </div>
          </div>

          <div className="dashboard-image">
            <img src={dashboardImg} alt="dashboard preview" />
          </div>
        </div>
      </section>
    </div>
  );
};

export default Pocetna;
