import React, { useEffect } from "react";
import { useState } from "react";
import { FaUserPlus } from "react-icons/fa";
import { FaUserMinus } from "react-icons/fa";
import axios from "axios";

export default function Dialog({
  show,
  action,
  title,
  buttonAction1,
  // buttonAction2,
  buttonLabel1,
  buttonLabel2,
  chat,
  data,
}) {
  const [users, SetUsers] = useState(data);
  const [selected, SetSelected] = useState();

  useEffect(()=>{
    SetSelected('');
    axios
    .get(`http://localhost:8080/api/users/chatUsersList?chat=${chat}`)
    .then((response) => {
      SetUsers(response.data);
    });
  },[show])

  const buttonAction2=(action)=>{
    if(action=="add"){
      axios
    .post("http://localhost:8080/api/messages", {
      message: selected+" has been added to the chat.",
      sender: selected,
      type: "special",
      group: chat,
    })
    .then((response) => {
      SetUsers(users.filter((item) => item.username!=selected))
    })
    .catch((error) => {
      console.log(error);
    });
    }else{
      axios
    .post("http://localhost:8080/api/messages", {
      message: selected+" has been removed from the chat.",
      sender: selected,
      type: "special",
      group: chat,
    })
    .then((response) => {
      SetUsers(users.filter((item) => item.username!=selected))
    })
    .catch((error) => {
      console.log(error);
    });
    }
  }

  if (show === false) {
    return <></>;
  }else{

  }
  return (
    <div className="overlay">
      <div className="dialog">
        <div className="dialog__content">
          {action == "add" ? (
            <FaUserPlus fontSize={45} />
          ) : (
            <FaUserMinus fontSize={45} />
          )}
          <h2 className="dialog__title">{title}</h2>
        </div>
        <div className="dialog_body">
          <div className="scroll">
            <div>
              {users?.map((item, index = 0) => (
                <div key={index} className={selected!==item.username?"useritem":"useritem-selected"} onClick={()=>{SetSelected(item.username)}}>{item.name}</div>
              ))}
            </div>
          </div>
        </div>

        <hr />

        <div className="dialog__footer">
          <button className="button wid-120 sp-20" onClick={buttonAction1}>
            {buttonLabel1}
          </button>
          <button className="button wid-120 sp-20" onClick={()=>buttonAction2(action)}>
            {buttonLabel2}
          </button>
        </div>
      </div>
    </div>
  );
}
