import axios from "axios";

export const sendDeviceTokenToServer = async (memberId, deviceToken) => {
    try {
      const response = await axios.post("http://localhost:8080/api/v1/notifications/device-token", {
        memberId,
        deviceToken,
      });
      console.log("Device token successfully sent to the server", response.data);
    } catch (error) {
      console.error("Error sending device token to the server: ", error);
    }
};