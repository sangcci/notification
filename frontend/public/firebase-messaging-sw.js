importScripts('https://www.gstatic.com/firebasejs/9.21.0/firebase-app-compat.js');
importScripts('https://www.gstatic.com/firebasejs/9.21.0/firebase-messaging-compat.js');

const firebaseConfig = {
    apiKey: "AIzaSyB9E5ZZZovRkA-DMuJgqvjWHwlftuS5vO8",
    authDomain: "allreva-f862c.firebaseapp.com",
    projectId: "allreva-f862c",
    storageBucket: "allreva-f862c.firebasestorage.app",
    messagingSenderId: "710001742388",
    appId: "1:710001742388:web:a7019a67b345d11f4b7061",
    measurementId: "G-P8H0L445LF"
  };

firebase.initializeApp(firebaseConfig);
const messaging = firebase.messaging();

messaging.onBackgroundMessage((payload) => {
  console.log('Background message received: ', payload);
  self.registration.showNotification(payload.notification.title, {
    body: payload.notification.body
  });
});
