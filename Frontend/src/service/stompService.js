import { Client } from "@stomp/stompjs";

const stompService = {
  client: null,

  connect() {
    return new Promise((resolve, reject) => {
      this.client = new Client({
        brokerURL: "ws://localhost:8080/chat", 
        connectHeaders: {
          login: "guest",
          passcode: "guest",
        },
        onConnect: () => {
          console.log("Conectado a STOMP");
          resolve();
        },
        onStompError: (error) => {
          console.error("Error en STOMP", error);
          reject(error);
        },
        reconnectDelay: 5000, 
      });

      this.client.activate();
    });
  },

  subscribe(destination, callback) {
    if (!this.client || !this.client.connected) {
      console.warn("No está conectado a STOMP. No se puede suscribir.");
      return;
    }

    this.client.subscribe(destination, (message) => {
      try {
        const body = JSON.parse(message.body);
        console.log("Mensaje recibido en subscribe:", body);
        callback(body);
      } catch (error) {
        console.error("Error al parsear el mensaje recibido:", error);
      }
    });
  },

  send(chatId, payload) {
    if (!this.client || !this.client.connected) return;

    this.client.publish({
      destination: `/app/chat/${chatId}/sendMessage`,
      body: JSON.stringify(payload),
    });
  },

  disconnect() {
    if (this.client) {
      this.client.deactivate();
      console.log("Desconectado de STOMP");
    }
  },
};

export default stompService;
