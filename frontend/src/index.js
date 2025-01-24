import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import { initializeApp } from "firebase/app";
import { getMessaging, onMessage } from "firebase/messaging";

const firebaseConfig = {
  apiKey: "AIzaSyB9E5ZZZovRkA-DMuJgqvjWHwlftuS5vO8",
  authDomain: "allreva-f862c.firebaseapp.com",
  projectId: "allreva-f862c",
  storageBucket: "allreva-f862c.firebasestorage.app",
  messagingSenderId: "710001742388",
  appId: "1:710001742388:web:a7019a67b345d11f4b7061",
  measurementId: "G-P8H0L445LF"
};

const app = initializeApp(firebaseConfig);
const messaging = getMessaging(app);

onMessage(messaging, (payload) => {
  console.log('Message received. ', payload);
});

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
