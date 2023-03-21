import "../Admin/AdminUpload.css";
import React, { useState } from "react";
import { FiLogOut } from "react-icons/all";
import { AiFillDelete } from "react-icons/all";
import admin from "./AdminModel";
import { useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";

const AdminUpload = () => {
  const [option, setOption] = useState("Java");
  const [progress, setProgress] = useState(0);
  const [isUploading, setisUploading] = useState(false);
  const [fileView, setFileView] = useState([]);
  const [annotate, setAnnotate] = useState([]);
  const [frontview, setFrontView] = useState([]);

  const changePage = useNavigate();

  const admin_name = admin.name;
  const onSubmit = (event) => {
    let surveyName = document.getElementById("surveyName").value;
    let surveyLanguage = option;
    let snippets = fileView;
    let annotations = annotate;
    let fileObject = {
      surveyName,
      surveyLanguage,
      snippets,
      annotations,
    };
    event.preventDefault();
    fetch("http://localhost:8080/admin/" + admin_name + "/survey/", {
      method: "POST",
      mode: "cors",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(fileObject),
    })
      .then((response) => {
        console.log(response.status);
        toast.success("Submitted Successfully!");
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const convertBase64 = (file) => {
    return new Promise((resolve, reject) => {
      const fileReader = new FileReader();
      fileReader.readAsDataURL(file);
      fileReader.onload = () => {
        resolve(fileReader.result);
      };

      fileReader.onerror = (err) => {
        reject(err);
      };
    });
  };

  let allFiles = [];
  let allFilesTemp = [];
  let allFilesEncode = [];

  const onUpload = async (e) => {
    const filePath = [];
    const arr = Array.from(e.target.files);
    arr.forEach((element) => {
      filePath.push(convertBase64(element));
    });
    allFilesTemp = await Promise.all(filePath);
    allFilesTemp.forEach((e) => {
      const temp = e.replace(/^data:(.*,)?/, "");
      allFiles.push(temp);
      const decodedText = atob(temp);
      console.log(decodedText);
      allFilesEncode.push(decodedText);
    });
    setFileView(allFiles);
    setFrontView(allFilesEncode);
  };

  const handleAdd = () => {
    const val = [...annotate, []];
    setAnnotate(val);
  };

  const handleChange = (event, i) => {
    const annotateInput = [...annotate];
    annotateInput[i] = event.target.value;
    setAnnotate(annotateInput);
  };
  const handleDelete = (d) => {
    const deleteAnnotate = [...annotate];
    deleteAnnotate.splice(d, 1);
    setAnnotate(deleteAnnotate);
  };

  const handleLogout = () => {
    changePage("/");
  };
  let imgGallery = frontview;

  return (
    <>
      <div className="snippets">
        <p>Upload Code Snippets</p>
      </div>
      <div className="annotations">
        <p className="annotate_p">Create Annotations</p>
        <button className="logout" onClick={handleLogout}>
          <FiLogOut />
        </button>
      </div>
      <div className="lang">
        <label className="label_s">Survey Name:</label>
        <input id="surveyName" type="text" className="survey_input"></input>
        <label className="label_s">Language</label>
        <select className="option" onChange={(e) => setOption(e.target.value)}>
          <option value="Java">Java</option>
          <option>Python</option>
          <option value="Javascript">javascript</option>
        </select>
        <input
          className="file"
          type="file"
          multiple
          onChange={onUpload}
        ></input>
      </div>
      {imgGallery.length > 0 ? (
        <div className="uploaded_snippets">
          {imgGallery.map((index) => (
            <pre className="render_view">{index}</pre>
          ))}
        </div>
      ) : null}
      <div className="uploaded_annotations">
        <button className="add" onClick={() => handleAdd()}>
          Add
        </button>
        {annotate.map((data, i) => {
          return (
            <div className="add_portion">
              <input
                className="annotate_input"
                value={data}
                onChange={(event) => handleChange(event, i)}
              />
              <button
                className="delete_annotate"
                onClick={() => handleDelete(i)}
              >
                <AiFillDelete />
              </button>
            </div>
          );
        })}
        <button className="submit" onClick={onSubmit}>
          Submit
        </button>
      </div>
      <ToastContainer />
    </>
  );
};

export default AdminUpload;
