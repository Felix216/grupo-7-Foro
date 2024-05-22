import axios from "axios";
const API_URL_PUBLICACIONES = "http://localhost:3000/posts";

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
    const resultado = await axios.get(API_URL_PUBLICACIONES);
    return resultado.data;

} catch (error) {
    console.log(`Error en obtener todas las publicaciones: ${error.message}`);
  }
};



export const buscarPublicacion= async (indentificador, valor)=> {

  try {
    const resultado = await axios.get(API_URL_PUBLICACIONES, {
      params:{
        [indentificador]: valor,
      }
    });
    
    return resultado.data;

} catch (error) {
    console.log(`Error en obtener todas las publicaciones: ${error.message}`);
  }

}


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
};



export const datosUsuarioLogeado = () => {
  const usuario = JSON.parse(localStorage.getItem("usuarioLogeado"));

  return usuario;
};


export const capitalizarPrimeraPalabra = (string) => {
  if (!string) return '';
  return string.charAt(0).toUpperCase() + string.slice(1);
};




