import axios from "axios";
//const API_URL_PUBLICACIONES = "http://localhost:3000/posts";


//Obtener Post Controller

const API_URL_POST ="http://localhost:8081/api/posts"
const API_URL_COMMENT = "http://localhost:8081/api/posts/comments"
const API_URL_INTERACTION = "http://localhost:8081/api/interactions"


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


export const obtenerComentarios = async(postId) => {
  try {
    const resultado = await axios.get(`${API_URL_COMMENT}/${postId}`);
    console.log(`Comentarios obtenidos:`, resultado.data);
    return resultado.data;

} catch (error) {
    console.log(`Error en obtener todas las Comentarios: ${error.message}`);
  }
}

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



export const buscarPublicacion= async (indentificador, valor)=> {

  try {
    const resultado = await axios.get(API_URL_POST, {
      params:{
        [indentificador]: valor,
      }
    });
    
    return resultado.data;

} catch (error) {
    console.log(`Error en obtener todas las publicaciones: ${error.message}`);
  }

}

/*
export const crearPublicacionPost = async (userId,username,tittle,category,content,image,likes,dislikes) => {
    
  try {
        const nuevaPublicacion = {
        userId,
        username,
        tittle,
        category,
        content,
        image,
        likes,
        dislikes
        };


        const resultado = await axios.post(`${API_URL_PUBLICACIONES}`, nuevaPublicacion);

        return resultado.data;

    } catch (error) {
        console.log(`Error en crear una publicacion: ${error.message}`);
    }
};*/

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



export const datosUsuarioLogeado = () => {
  const usuario = JSON.parse(localStorage.getItem("usuarioLogeado"));

  return usuario;
};


export const capitalizarPrimeraPalabra = (string) => {
  if (!string) return '';
  return string.charAt(0).toUpperCase() + string.slice(1);
};




