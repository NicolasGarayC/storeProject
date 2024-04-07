// src/services/apiService.js
const API_BASE_URL = '/api'; // Asegúrate de ajustar esto a la base de tu endpoint en Spring Boot

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

  // Para los métodos GET y HEAD, no enviar un cuerpo.
  if (method === 'GET' || method === 'HEAD') {
    delete config.body;
  }

  try {
    const response = await fetch(url, config);
    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }
    // Para operaciones DELETE, la respuesta puede no tener contenido.
    return response.status === 204 ? null : await response.json();
  } catch (error) {
    console.error("Error making request:", error);
    throw error;
  }
};

/**
 * Operaciones CRUD genéricas
 */
const apiService = {
  get: (endpoint) => makeRequest(endpoint, 'GET'),
  post: (endpoint, body) => makeRequest(endpoint, 'POST', body),
  put: (endpoint, body) => makeRequest(endpoint, 'PUT', body),
  delete: (endpoint) => makeRequest(endpoint, 'DELETE')
};

export default apiService;
