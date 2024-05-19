import axios from "axios";
const API_URL_PUBLICACIONES = "http://localhost:3000/publicaciones";

export const obtenerPublicaciones = async () => {
  try {
    const resultado = await axios.get(API_URL_PUBLICACIONES);
    return resultado.data;

} catch (error) {
    console.log(`Error en obtener todas las publicaciones: ${error.message}`);
  }
};






export const crearPublicacionPost = async (userId,titulo,categoria,imagen,descripcion,meGusta,NoMeGusta) => {
    try {
        const nuevaPublicacion = {
        userId,
        titulo,
        categoria,
        descripcion,
        imagen,
        meGusta,
        NoMeGusta
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

