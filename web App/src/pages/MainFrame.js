import { useState } from "react";
import Chat from "./Chat";
import image from "./image1.jpg";

const MainFrame = () => {
  
  const [chatDetatils, setChatDetatils] = useState([
    { id: 1, name: "GlobalChat", chatref: "general" },
    { id: 2, name: "Chat1", chatref: "chat1" },
    { id: 3, name: "Chat2", chatref: "chat2" },
  ]);

    const [chatref,setChatref] = useState(null);
    const [chatname,setChatName] = useState(null);

  const goToChat=(chatref,name)=>{
      console.log("chat "+chatref+" clicked");
      setChatref(chatref);
      setChatName(name);
  }

  return (
    <>
      <div className="container">
        <div className="MainFrame">
          <div className="sidebar">
            <div className="mainframe-header pd-20">
              <h1 className="mheader">mChat</h1>
            </div>
            <div className="hr-main"></div>
            {chatDetatils?.map((item , index=0) => (
              <div className="sidebar-item pd-20" key={index} onClick={()=>{goToChat(item.chatref,item.name)}}>
                <div className="avatar">
                  <span>{(item.name.charAt(0))}</span>
                </div>
                <div className="details">
                  <span className="pd-20">{item.name}</span>
                </div>
              </div>
            ))}
            <div className="hr-main"></div>
          </div>
          {
            chatref? 
            (
            <Chat chatref={chatref} name={chatname} />
            )
            :(
            <div className="chatContainer">
              <div className="imageContainer">
              <img src={image} alt="chat" className="image"/>
              <span className="topicName">mChat</span>
              <span>Welcome to mChat Platform.</span>
              <span>Click your chat to view messages.</span>
              </div>
            </div>
            )
          }
          
        </div>
      </div>
    </>
  );
};

export default MainFrame;
