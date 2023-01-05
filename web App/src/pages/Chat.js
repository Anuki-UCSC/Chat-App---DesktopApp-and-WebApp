import { useEffect, useState } from "react";
import { FaUserPlus } from "react-icons/fa";
import { FaUserMinus } from "react-icons/fa";
import { IoMdSend } from "react-icons/io";
import Dialog from "../components/Dialog";
import axios from "axios";


const Chat = ({ chatref, name }) => {
  const [messages, SetMessages] = useState([]);
  const [users, SetUsers] = useState([]);

  const [msg, SetMsg] = useState("");
  const [chat, Setchat] = useState(null);
  const [currentUser, SetCurrentUser] = useState(null);

  const [addUserDialog, SetAddUserDialog] = useState(false);
  const [removeUserDialog, SetRemoveUserDialog] = useState(false);
  useEffect(() => {
    const interval = setInterval(() => {
      Setchat(chatref);
      console.log("loca storGE :" + localStorage.getItem("username"));
      SetCurrentUser(localStorage.getItem("username"));
      axios
        .get(`http://localhost:8080/api/messages?chat=${chatref}`)
        .then((response) => {
          SetMessages(response.data);
        });
        console.log("chat reloaded")
    }, 500);
    return () => clearInterval(interval);
  }, [chatref]);


  const sendMsg = (e) => {
    e.preventDefault();
    axios
      .post("http://localhost:8080/api/messages", {
        message: msg,
        sender: currentUser,
        type: "normal",
        group: chat,
      })
      .then((response) => {
        SetMsg("")
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const userListadd = () => {
    SetAddUserDialog(true);
  };

  const addUsersToChat = (selectedUserName) => {
    axios
    .post("http://localhost:8080/api/messages", {
      message: selectedUserName+" has been added to the system.",
      sender: selectedUserName,
      type: "special",
      group: chat,
    })
    .then((response) => {
      SetMsg("")
    })
    .catch((error) => {
      console.log(error);
    });
  };

  const RemoveUsersFromChat = () => {};

  const close = () => {
    SetAddUserDialog(false);
    SetRemoveUserDialog(false);
  };

  return (
    <>
      <div className="chatContainer">
        <div className="mainframe-header pd-20">
          <div className="group">
            <div className="avatar">
              <span>{name.charAt(0)}</span>
            </div>
            <div className="details">
              <span className="pd-20">{name}</span>
            </div>
          </div>
          {chatref == "general" ? (
            <div></div>
          ) : (
            <div className="group">
              <div
                className="user-button"
                onClick={() => {
                  userListadd();
                }}
              >
                <div className="icon">
                  <FaUserPlus fontSize={40} />
                </div>
              </div>
              <div
                className="user-button"
                onClick={() => {
                  SetRemoveUserDialog(true);
                  console.log("addDialog");
                }}
              >
                <div className="icon">
                  <FaUserMinus fontSize={40} />
                </div>
              </div>
            </div>
          )}
        </div>

        <div className="chat scroller">
          {messages?.map((element, index) => {
            if (element.type == "normal") {
              if (element.sender !== currentUser) {
                return (
                  <div className="message-item justify-l" key={index}>
                    <div className="avatar-small">
                      <span>{element.sender.charAt(0)}</span>
                    </div>
                    <span className="message reciever">{element.message}</span>
                  </div>
                );
              } else {
                return (
                  <div className="message-item justify-r" key={index}>
                    <span className="message sender">{element.message}</span>
                    <div className="avatar-small">
                      <span>{element.sender.charAt(0)}</span>
                    </div>
                  </div>
                );
              }
            } else {
              return (
                <div className="message-item justify-c" key={index}>
                  <span className="message special">{element.message}</span>
                </div>
              );
            }
          })}
        </div>

        <div className="msg-footer">
          <input
            type="text"
            className="userNameTf sp-20 wid-adgestable"
            name="msg"
            value={msg}
            autoComplete="off"
            onChange={(e) => SetMsg(e.target.value)}
            placeholder="Type Here..."
          />
          <button
            className="button sp-20 sendbtn"
            onClick={(e) => {
              sendMsg(e);
            }}
          >
            <IoMdSend fontSize={25} />
            SEND
          </button>
        </div>
      </div>

      <Dialog
        show={addUserDialog}
        action="add"
        title="Add New Users"
        buttonLabel1="CLOSE"
        buttonLabel2="ADD"
        buttonAction1={close}
        // buttonAction2={addUsersToChat}
        chat={chatref}
        data={users}
      />
      <Dialog
        show={removeUserDialog}
        action="remove"
        title="Remove Users"
        buttonLabel1="CLOSE"
        buttonLabel2="REMOVE"
        buttonAction1={close}
        // buttonAction2={RemoveUsersFromChat}
        chat={chatref}
        data={users}
      />
    </>
  );
};

export default Chat;
