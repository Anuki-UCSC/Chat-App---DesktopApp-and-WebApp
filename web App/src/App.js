import "./App.css";
import "./dialog.css";
import Login from "./pages/Login";
import MainFrame from "./pages/MainFrame";
import { BrowserRouter, Route, Routes } from "react-router-dom";

function App() {
  return (
    <div className="App">
        <Routes>
          <Route path="/mainframe" element={<MainFrame/>} />
          <Route path="/" element={<Login/>} />
        </Routes>
    </div>
  );
}

export default App;
