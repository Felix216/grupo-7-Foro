import axios from "axios";

//Obtener Post Controller

const API_URL_POST ="http://localhost:8081/api/posts"
const API_URL_COMMENT = "http://localhost:8081/api/posts/comments"
const API_URL_INTERACTION = "http://localhost:8081/api/interactions"

export const obtenerUsuarioPorID = async (userID) => {
  try {
    const resultado = await axios.get(`http://localhost:8081/api/users/${userID}`);
    console.log("Usuario encontrado: ", resultado.data)
    return resultado.data;

} catch (error) {
    console.log(`Error al buscar el Usuario: ${error.message}`);
  } 
}

export const colorDictionary = {
  "noticias": "bg-red-600" ,
  "deportes": "bg-lime-500",
  "ayuda":"bg-purple-500",
  "humor":"bg-yellow-400",
  "juegos":"bg-blue-500",
  "otros":"bg-gray-400",

};
export const obtenerPublicaciones = async () => {
  try {
    const resultado = await axios.get(API_URL_POST);
    console.log("Resultado de todas las publicaciones : ",resultado.data)
    return resultado.data;

} catch (error) {
    console.log(`Error en obtener todas las publicaciones: ${error.message}`);
  }
};



export const obtenerInteraccion = async (postId) => {
  try {
    const resultado = await axios.get(`${API_URL_INTERACTION}/${postId}`);
    console.log("Resultado de todas las INTERACCIONES : ",resultado.data)
    return resultado.data;

} catch (error) {
    console.log(`Error en obtener todas las Interacciones: ${error.message}`);
  }
};

export const obtenerPublicacionesPopulares= async () => {
  try {
    const resultado = await axios.get(`${API_URL_POST}/popular`);
    console.log("Resultado de todas las POST POPULARES : ",resultado.data)
    return resultado.data;

} catch (error) {
    console.log(`Error en obtener todas las Interacciones: ${error.message}`);
  }
};

export const obtenerPublicacionesPorId= async (postId) => {
  try {
    const resultado = await axios.get(`${API_URL_POST}/${postId}`);
    console.log("Resultado del post "+postId+" | POST : ",resultado.data)
    return resultado.data;

} catch (error) {
    console.log(`Error en obtener todas las Interacciones: ${error.message}`);
  }
};


export const obtenerPublicacionesPorCategoria = async (categoria) => {
  try {
    const response = await axios.get(`${API_URL_POST}/category/${categoria}`);
    return response.data;
  } catch (error) {
    console.error(`Error al obtener publicaciones por categoría: ${error.message}`);
    throw error;
  }
};

// Crear una nueva publicación
export const crearPublicacionPost = async (userId, tittle, category, content, image) => {
  try {
    const formData = new FormData();
    formData.append("userId", userId);
    formData.append("tittle", tittle);
    formData.append("category", category);
    formData.append("content", content);
    formData.append("image", image);

    const resultado = await axios.post(API_URL_POST, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });

    return resultado.data;
  } catch (error) {
    console.log(`Error en crear una publicación: ${error.message}`);
  }
};


// INTERACTION PETICIONES 
export const likePost = async (userId, postId) => {
  try {
    const response = await axios.post(`${API_URL_INTERACTION}/like`, null, {
      params: {
        userId: userId,
        postId: postId,
      },
    });
    console.log("Like interaction created: ", response.data);
    return response.data;
  } catch (error) {
    console.error(`Error creating like interaction: ${error.message}`);
    throw error;
  }
};

export const dislikePost = async (userId, postId) => {
  try {
    const response = await axios.post(`${API_URL_INTERACTION}/dislike`, null, {
      params: {
        userId: userId,
        postId: postId,
      },
    });
    console.log("Dislike interaction created: ", response.data);
    return response.data;
  } catch (error) {
    console.error(`Error creating dislike interaction: ${error.message}`);
    throw error;
  }
};


//COMENTARIOS 


export const obtenerComentarios = async(postId) => {
  try {
    const resultado = await axios.get(`${API_URL_COMMENT}/${postId}`);
    console.log(`Comentarios obtenidos:`, resultado.data);
    return resultado.data;

} catch (error) {
    console.log(`Error en obtener todas las Comentarios: ${error.message}`);
  }
}

export const crearComentario = async (content, userId, postId) => {
  return await axios.post(API_URL_COMMENT, null, {
      params: {
          content: content,
          userId: userId,
          postId: postId
      }
  });
};



export const datosUsuarioLogeado = () => {
  const usuario = JSON.parse(localStorage.getItem("usuarioLogeado"));

  return usuario;
};


export const capitalizarPrimeraPalabra = (string) => {
  if (!string) return '';
  return string.charAt(0).toUpperCase() + string.slice(1);
};




