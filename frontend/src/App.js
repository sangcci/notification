import React, { useState } from 'react';
import { getMessaging, getToken } from "firebase/messaging";
import { sendDeviceTokenToServer } from './api/firebaseService';

function App() {
  const [memberId, setMemberId] = useState("");

  const requestPermission = async () => {
    if (!memberId) {
      alert("Member ID를 입력하세요!");
      return;
    }

    const messaging = getMessaging();
    try {
      const token = await getToken(messaging, { vapidKey: process.env.REACT_APP_FIREBASE_VAPID_KEY });
      console.log("FCM Token:", token);

      alert("Notification permission granted. Token: " + token);

      await sendDeviceTokenToServer(memberId, token);
    } catch (error) {
      console.error("Error getting FCM token: ", error);
    }
  };

  return (
    <div style={{ textAlign: "center", marginTop: "50px" }}>
      <h1>FCM 테스트</h1>

      <input
        type="text"
        placeholder="Enter Member ID"
        value={memberId}
        onChange={(e) => setMemberId(e.target.value)}
        style={{ marginBottom: "20px", padding: "10px", fontSize: "16px" }}
      />
      <br />

      <button onClick={requestPermission}>알림 권한 요청</button>
    </div>
  );
}

export default App;
