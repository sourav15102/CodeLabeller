import React from "react";

const SurveyItem = ({ survey }) => {
  return (
    <div>
      <h3>{survey.title}</h3>
      <p>{survey.description}</p>
    </div>
  );
};

export default SurveyItem;
