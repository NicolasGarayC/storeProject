const API_BASE_URL = 'http://localhost:3000/'; 

/**
 * Función genérica para realizar solicitudes HTTP.
 * 
 * @param {string} endpoint El endpoint específico de la API a solicitar.
 * @param {string} method El método HTTP a utilizar (GET, POST, PUT, DELETE).
 * @param {Object|null} body El cuerpo de la solicitud para enviar, si es necesario.
 * @returns {Promise<any>} La respuesta de la API como una promesa.
 */
const makeRequest = async (endpoint, method, body = null) => {
  const url = `${API_BASE_URL}${endpoint}`;
  const headers = {
    'Content-Type': 'application/json'
  };

  const config = {
    method: method,
    headers: headers,
    body: body ? JSON.stringify(body) : null,
  };

  if (method === 'GET' || method === 'HEAD') {
    delete config.body;
  }

  try {
    const response = await fetch(url, config);
    const data = await response.json(); // Intenta parsear la respuesta siempre
    if (!response.ok) {
      throw new Error(data.message || `HTTP error! status: ${response.status}`); // Usa un mensaje personalizado si está disponible
    }
    return data;
  } catch (error) {
    console.error("Error making request:", error);
    throw error; // Re-lanza el error con la información útil
  }
};


const apiService = {
  get: (endpoint) => makeRequest(endpoint, 'GET'),
  post: (endpoint, body) => makeRequest(endpoint, 'POST', body),
  put: (endpoint, body) => makeRequest(endpoint, 'PUT', body),
  delete: (endpoint) => makeRequest(endpoint, 'DELETE')
};

export default apiService;
