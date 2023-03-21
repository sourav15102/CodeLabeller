import React from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "./AdminView.css";
import admin from "./AdminModel";
import { useEffect } from "react";
import { useState } from "react";
import SurveyItem from "./SurveyItem";

const AdminView = () => {
  const changePage = useNavigate();
  const [surveys, setSurveys] = useState([
    {
      surveyId: 1,
      title: "Survey 1",
      description: "This is the first survey.",
    },
    {
      surveyId: 2,
      title: "Survey 2",
      description: "This is the second survey.",
    },
    {
      surveyId: 3,
      title: "Survey 3",
      description: "This is the third survey.",
    },
  ]);
  const admin_name = admin.name;

  const onLogout = () => {
    changePage("/");
  };

  const onHome = () => {
    changePage("/admin/home");
  };

  useEffect(() => {
    axios
      .get("URL_FOR_SURVEYS")
      .then((response) => {
        setSurveys(response.data);
      })
      .then(() => {
        changePage("/admin/home/viewSurveys");
      })
      .catch((error) => {
        console.error("Error loading surveys:", error);
      });
  }, []);

  const handleSurveyClick = (surveyId) => {
    changePage(`/admin/home/viewSurveys/${surveyId}`);
  };

  return (
    <div>
      <header className="header">
        <h1 className="header-title">List of Surveys</h1>
        <div className="header-buttons">
          <button className="header-button" onClick={onHome}>
            Home
          </button>
          <button className="logout-button" onClick={onLogout}>
            Logout
          </button>
        </div>
      </header>
      <div className="surveys-container">
        <ul className="surveys-list">
          {surveys.map((survey) => (
            <li
              key={survey.surveyId}
              className="survey-item"
              onClick={() => handleSurveyClick(survey.surveyId)}
            >
              <SurveyItem survey={survey} />
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default AdminView;
