<template>
  <div>
    <div v-if="mostrar && publicacionesOrdenadas.length > 0">
      <article class="overflow-hidden rounded-lg shadow transition hover:shadow-2xl px-6 my-4 bg-white "
        v-for="(publicacion, index) in publicacionesOrdenadas" :key="index">
        <div class="flex-auto justify-center my-4">
          <span class="mx-2 text-xl font-bold">{{ publicacion.usuarios.username }}</span>
          <span class="mx-2 rounded-full px-4 py-1 text-base text-white "
            :class="agregarFondoCategoria(publicacion.publicaciones.category)">
            {{ capitalizarPrimeraPalabra(publicacion.publicaciones.category) }}
          </span>
        </div>

        <img alt="" :src="publicacion.publicaciones.image" class="h-56 w-full object-cover" />

        <div class="bg-white p-4 sm:p-6">

          <h3 class="mt-0.5 text-lg text-gray-900">{{ publicacion.publicaciones.tittle }}</h3>

          <p class="mt-2 line-clamp-3 text-sm/relaxed text-gray-500">{{ publicacion.publicaciones.content }}</p>

          <div class="flex ">


            <button v-on:click="darMeGusta()"
              class="flex items-center mr-2 p-2 rounded-lg  hover:scale-125 transition duration-300">

              <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6 mr-1">
                <path
                  d="M7.493 18.5c-.425 0-.82-.236-.975-.632A7.48 7.48 0 0 1 6 15.125c0-1.75.599-3.358 1.602-4.634.151-.192.373-.309.6-.397.473-.183.89-.514 1.212-.924a9.042 9.042 0 0 1 2.861-2.4c.723-.384 1.35-.956 1.653-1.715a4.498 4.498 0 0 0 .322-1.672V2.75A.75.75 0 0 1 15 2a2.25 2.25 0 0 1 2.25 2.25c0 1.152-.26 2.243-.723 3.218-.266.558.107 1.282.725 1.282h3.126c1.026 0 1.945.694 2.054 1.715.045.422.068.85.068 1.285a11.95 11.95 0 0 1-2.649 7.521c-.388.482-.987.729-1.605.729H14.23c-.483 0-.964-.078-1.423-.23l-3.114-1.04a4.501 4.501 0 0 0-1.423-.23h-.777ZM2.331 10.727a11.969 11.969 0 0 0-.831 4.398 12 12 0 0 0 .52 3.507C2.28 19.482 3.105 20 3.994 20H4.9c.445 0 .72-.498.523-.898a8.963 8.963 0 0 1-.924-3.977c0-1.708.476-3.305 1.302-4.666.245-.403-.028-.959-.5-.959H4.25c-.832 0-1.612.453-1.918 1.227Z" />
              </svg>
              <span class="font-semibold text-xl"> {{ contarLikes(publicacion.interaccion) }}</span>
            </button>

            <button v-on:click="darNoMeGusta()"
              class="flex items-center mx-2 p-2 rounded-lg  hover:scale-125 transition duration-300">
              <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6 mr-1">
                <path
                  d="M15.73 5.5h1.035A7.465 7.465 0 0 1 18 9.625a7.465 7.465 0 0 1-1.235 4.125h-.148c-.806 0-1.534.446-2.031 1.08a9.04 9.04 0 0 1-2.861 2.4c-.723.384-1.35.956-1.653 1.715a4.499 4.499 0 0 0-.322 1.672v.633A.75.75 0 0 1 9 22a2.25 2.25 0 0 1-2.25-2.25c0-1.152.26-2.243.723-3.218.266-.558-.107-1.282-.725-1.282H3.622c-1.026 0-1.945-.694-2.054-1.715A12.137 12.137 0 0 1 1.5 12.25c0-2.848.992-5.464 2.649-7.521C4.537 4.247 5.136 4 5.754 4H9.77a4.5 4.5 0 0 1 1.423.23l3.114 1.04a4.5 4.5 0 0 0 1.423.23ZM21.669 14.023c.536-1.362.831-2.845.831-4.398 0-1.22-.182-2.398-.52-3.507-.26-.85-1.084-1.368-1.973-1.368H19.1c-.445 0-.72.498-.523.898.591 1.2.924 2.55.924 3.977a8.958 8.958 0 0 1-1.302 4.666c-.245.403.028.959.5.959h1.053c.832 0 1.612-.453 1.918-1.227Z" />
              </svg>
              <span class="font-semibold text-xl">{{ contarDislikes(publicacion.dislikes) }}</span>
            </button>
            <button v-on:click="agregarComentario()"
              class="flex items-center mx-2 p-2 rounded-lg   hover:scale-125 transition duration-300">
              <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6 mr-1">
                <path fill-rule="evenodd"
                  d="M4.804 21.644A6.707 6.707 0 0 0 6 21.75a6.721 6.721 0 0 0 3.583-1.029c.774.182 1.584.279 2.417.279 5.322 0 9.75-3.97 9.75-9 0-5.03-4.428-9-9.75-9s-9.75 3.97-9.75 9c0 2.409 1.025 4.587 2.674 6.192.232.226.277.428.254.543a3.73 3.73 0 0 1-.814 1.686.75.75 0 0 0 .44 1.223ZM8.25 10.875a1.125 1.125 0 1 0 0 2.25 1.125 1.125 0 0 0 0-2.25ZM10.875 12a1.125 1.125 0 1 1 2.25 0 1.125 1.125 0 0 1-2.25 0Zm4.875-1.125a1.125 1.125 0 1 0 0 2.25 1.125 1.125 0 0 0 0-2.25Z"
                  clip-rule="evenodd" />
              </svg>
              <span class="font-semibold text-xl">{{ publicacion.comentarios.length }}</span>
            </button>


          </div>




          <div>

            <router-link :to="{ name: 'verPublicacion', params: { postId: publicacion.publicaciones.id } }">Ver
              mas</router-link>
          </div>
        </div>




      </article>

    </div>
    <article v-else-if="mostrarFiltro"
      class="overflow-hidden rounded-lg shadow transition hover:shadow-2xl px-6 my-4  bg-white "
      v-for="(publicacionfiltrada, indexFiltro) in publicacionesFiltradas" :key="indexFiltro">
      <h1>PUBLICACIONES FILTRADAS SIUUU</h1>
      <div v-if="publicacionfiltrada.usuarios" class="flex-auto justify-center my-4">
        <span class="mx-2 text-xl font-bold">{{ publicacionfiltrada.usuarios.username }}</span>
        <span class="mx-2 rounded-full px-4 py-1  text-base text-white "
          :class="agregarFondoCategoria(publicacionfiltrada.publicaciones.category)">
          {{ capitalizarPrimeraPalabra(publicacionfiltrada.publicaciones.category) }}
        </span>
      </div>
      <img alt="" :src="publicacionfiltrada.publicaciones.image" class="h-56 w-full object-cover" />

      <div class="bg-white p-4 sm:p-6">

        <h3 class="mt-0.5 text-lg text-gray-900">{{ publicacionfiltrada.publicaciones.tittle }} </h3>

        <p class="mt-2 line-clamp-3 text-sm/relaxed text-gray-500">{{ publicacionfiltrada.publicaciones.content }}</p>

        <div class="flex ">


          <button v-on:click="darMeGusta()"
            class="flex items-center mr-2 p-2 rounded-lg  hover:scale-125 transition duration-300">

            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6 mr-1">
              <path
                d="M7.493 18.5c-.425 0-.82-.236-.975-.632A7.48 7.48 0 0 1 6 15.125c0-1.75.599-3.358 1.602-4.634.151-.192.373-.309.6-.397.473-.183.89-.514 1.212-.924a9.042 9.042 0 0 1 2.861-2.4c.723-.384 1.35-.956 1.653-1.715a4.498 4.498 0 0 0 .322-1.672V2.75A.75.75 0 0 1 15 2a2.25 2.25 0 0 1 2.25 2.25c0 1.152-.26 2.243-.723 3.218-.266.558.107 1.282.725 1.282h3.126c1.026 0 1.945.694 2.054 1.715.045.422.068.85.068 1.285a11.95 11.95 0 0 1-2.649 7.521c-.388.482-.987.729-1.605.729H14.23c-.483 0-.964-.078-1.423-.23l-3.114-1.04a4.501 4.501 0 0 0-1.423-.23h-.777ZM2.331 10.727a11.969 11.969 0 0 0-.831 4.398 12 12 0 0 0 .52 3.507C2.28 19.482 3.105 20 3.994 20H4.9c.445 0 .72-.498.523-.898a8.963 8.963 0 0 1-.924-3.977c0-1.708.476-3.305 1.302-4.666.245-.403-.028-.959-.5-.959H4.25c-.832 0-1.612.453-1.918 1.227Z" />
            </svg>

            <span class="font-semibold text-xl">
              {{ contarLikes(publicacionfiltrada.interaccion) }}</span>
          </button>

          <button v-on:click="darNoMeGusta()"
            class="flex items-center mx-2 p-2 rounded-lg  hover:scale-125 transition duration-300">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6 mr-1">
              <path
                d="M15.73 5.5h1.035A7.465 7.465 0 0 1 18 9.625a7.465 7.465 0 0 1-1.235 4.125h-.148c-.806 0-1.534.446-2.031 1.08a9.04 9.04 0 0 1-2.861 2.4c-.723.384-1.35.956-1.653 1.715a4.499 4.499 0 0 0-.322 1.672v.633A.75.75 0 0 1 9 22a2.25 2.25 0 0 1-2.25-2.25c0-1.152.26-2.243.723-3.218.266-.558-.107-1.282-.725-1.282H3.622c-1.026 0-1.945-.694-2.054-1.715A12.137 12.137 0 0 1 1.5 12.25c0-2.848.992-5.464 2.649-7.521C4.537 4.247 5.136 4 5.754 4H9.77a4.5 4.5 0 0 1 1.423.23l3.114 1.04a4.5 4.5 0 0 0 1.423.23ZM21.669 14.023c.536-1.362.831-2.845.831-4.398 0-1.22-.182-2.398-.52-3.507-.26-.85-1.084-1.368-1.973-1.368H19.1c-.445 0-.72.498-.523.898.591 1.2.924 2.55.924 3.977a8.958 8.958 0 0 1-1.302 4.666c-.245.403.028.959.5.959h1.053c.832 0 1.612-.453 1.918-1.227Z" />
            </svg>
            <span class="font-semibold text-xl">{{ contarDislikes(publicacionfiltrada.interaccion) }}</span>
          </button>
          <button v-on:click="agregarComentario()"
            class="flex items-center mx-2 p-2 rounded-lg   hover:scale-125 transition duration-300">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6 mr-1">
              <path fill-rule="evenodd"
                d="M4.804 21.644A6.707 6.707 0 0 0 6 21.75a6.721 6.721 0 0 0 3.583-1.029c.774.182 1.584.279 2.417.279 5.322 0 9.75-3.97 9.75-9 0-5.03-4.428-9-9.75-9s-9.75 3.97-9.75 9c0 2.409 1.025 4.587 2.674 6.192.232.226.277.428.254.543a3.73 3.73 0 0 1-.814 1.686.75.75 0 0 0 .44 1.223ZM8.25 10.875a1.125 1.125 0 1 0 0 2.25 1.125 1.125 0 0 0 0-2.25ZM10.875 12a1.125 1.125 0 1 1 2.25 0 1.125 1.125 0 0 1-2.25 0Zm4.875-1.125a1.125 1.125 0 1 0 0 2.25 1.125 1.125 0 0 0 0-2.25Z"
                clip-rule="evenodd" />
            </svg>
            <span v-if="publicacionfiltrada.comentarios.length > 0" class="font-semibold text-xl">

              {{ publicacionfiltrada.comentarios.length }}
            </span>
            <span v-else class="font-semibold text-xl">{{ 0 }}</span>
          </button>



        </div>




        <div>

          <router-link :to="{ name: 'verPublicacion', params: { postId: publicacionfiltrada.publicaciones.id } }">Ver
            mas</router-link>
        </div>
      </div>
    </article>






    <article v-else class="overflow-hidden rounded-lg shadow transition hover:shadow-2xl px-6 my-4  bg-white "
      v-for="(publicacion, index) in publicaciones" :key="index">
      <div v-if="publicacion.usuarios" class="flex-auto justify-center my-4">
        <span class="mx-2 text-xl font-bold">{{ publicacion.usuarios.username }}</span>
        <span class="mx-2 rounded-full px-4 py-1  text-base text-white "
          :class="agregarFondoCategoria(publicacion.publicacion.category)">
          {{ capitalizarPrimeraPalabra(publicacion.publicacion.category) }}
        </span>
      </div>
      <img alt="" :src="publicacion.publicacion.image" class="h-56 w-full object-cover" />

      <div class="bg-white p-4 sm:p-6">

        <h3 class="mt-0.5 text-lg text-gray-900">{{ publicacion.publicacion.tittle }} </h3>

        <p class="mt-2 line-clamp-3 text-sm/relaxed text-gray-500">{{ publicacion.publicacion.content }}</p>

        <div class="flex ">


          <button v-on:click="darMeGusta()"
            class="flex items-center mr-2 p-2 rounded-lg  hover:scale-125 transition duration-300">

            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6 mr-1">
              <path
                d="M7.493 18.5c-.425 0-.82-.236-.975-.632A7.48 7.48 0 0 1 6 15.125c0-1.75.599-3.358 1.602-4.634.151-.192.373-.309.6-.397.473-.183.89-.514 1.212-.924a9.042 9.042 0 0 1 2.861-2.4c.723-.384 1.35-.956 1.653-1.715a4.498 4.498 0 0 0 .322-1.672V2.75A.75.75 0 0 1 15 2a2.25 2.25 0 0 1 2.25 2.25c0 1.152-.26 2.243-.723 3.218-.266.558.107 1.282.725 1.282h3.126c1.026 0 1.945.694 2.054 1.715.045.422.068.85.068 1.285a11.95 11.95 0 0 1-2.649 7.521c-.388.482-.987.729-1.605.729H14.23c-.483 0-.964-.078-1.423-.23l-3.114-1.04a4.501 4.501 0 0 0-1.423-.23h-.777ZM2.331 10.727a11.969 11.969 0 0 0-.831 4.398 12 12 0 0 0 .52 3.507C2.28 19.482 3.105 20 3.994 20H4.9c.445 0 .72-.498.523-.898a8.963 8.963 0 0 1-.924-3.977c0-1.708.476-3.305 1.302-4.666.245-.403-.028-.959-.5-.959H4.25c-.832 0-1.612.453-1.918 1.227Z" />
            </svg>

            <span class="font-semibold text-xl">
              {{ contarLikes(publicacion.interaccion) }}</span>
          </button>

          <button v-on:click="darNoMeGusta()"
            class="flex items-center mx-2 p-2 rounded-lg  hover:scale-125 transition duration-300">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6 mr-1">
              <path
                d="M15.73 5.5h1.035A7.465 7.465 0 0 1 18 9.625a7.465 7.465 0 0 1-1.235 4.125h-.148c-.806 0-1.534.446-2.031 1.08a9.04 9.04 0 0 1-2.861 2.4c-.723.384-1.35.956-1.653 1.715a4.499 4.499 0 0 0-.322 1.672v.633A.75.75 0 0 1 9 22a2.25 2.25 0 0 1-2.25-2.25c0-1.152.26-2.243.723-3.218.266-.558-.107-1.282-.725-1.282H3.622c-1.026 0-1.945-.694-2.054-1.715A12.137 12.137 0 0 1 1.5 12.25c0-2.848.992-5.464 2.649-7.521C4.537 4.247 5.136 4 5.754 4H9.77a4.5 4.5 0 0 1 1.423.23l3.114 1.04a4.5 4.5 0 0 0 1.423.23ZM21.669 14.023c.536-1.362.831-2.845.831-4.398 0-1.22-.182-2.398-.52-3.507-.26-.85-1.084-1.368-1.973-1.368H19.1c-.445 0-.72.498-.523.898.591 1.2.924 2.55.924 3.977a8.958 8.958 0 0 1-1.302 4.666c-.245.403.028.959.5.959h1.053c.832 0 1.612-.453 1.918-1.227Z" />
            </svg>
            <span class="font-semibold text-xl">{{ contarDislikes(publicacion.interaccion) }}</span>
          </button>
          <button v-on:click="agregarComentario()"
            class="flex items-center mx-2 p-2 rounded-lg   hover:scale-125 transition duration-300">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6 mr-1">
              <path fill-rule="evenodd"
                d="M4.804 21.644A6.707 6.707 0 0 0 6 21.75a6.721 6.721 0 0 0 3.583-1.029c.774.182 1.584.279 2.417.279 5.322 0 9.75-3.97 9.75-9 0-5.03-4.428-9-9.75-9s-9.75 3.97-9.75 9c0 2.409 1.025 4.587 2.674 6.192.232.226.277.428.254.543a3.73 3.73 0 0 1-.814 1.686.75.75 0 0 0 .44 1.223ZM8.25 10.875a1.125 1.125 0 1 0 0 2.25 1.125 1.125 0 0 0 0-2.25ZM10.875 12a1.125 1.125 0 1 1 2.25 0 1.125 1.125 0 0 1-2.25 0Zm4.875-1.125a1.125 1.125 0 1 0 0 2.25 1.125 1.125 0 0 0 0-2.25Z"
                clip-rule="evenodd" />
            </svg>
            <span v-for="(comentario, idx) in publicacion.comentarios" :key="idx"></span>
            <span v-if="publicacion.comentarios.length > 0" class="font-semibold text-xl">

              {{ publicacion.comentarios.length }}
            </span>
            <span v-else class="font-semibold text-xl">{{ 0 }}</span>
          </button>



        </div>




        <div>

          <router-link :to="{ name: 'verPublicacion', params: { postId: publicacion.publicacion.id } }">Ver
            mas</router-link>
        </div>
      </div>




    </article>
  </div>







</template>

<script>
import { colorDictionary, obtenerPublicaciones, obtenerComentarios, obtenerInteraccion, obtenerUsuarioPorID } from '@/services/foroService';
import { capitalizarPrimeraPalabra } from '@/services/foroService';
export default {

  data() {
    return {
      categoriaSeleccionada: null,
      publicaciones: [],
      colorDictionary,
    }

  },
  props: {
    publicacionesOrdenadas: {
      type: Array,
      required: true,
    },
    mostrar: {
      type: Boolean,
      default: false,
    },
    publicacionesFiltradas: {
      type: Array,
      required: true
    },
    mostrarFiltro: {
      type: Boolean,
      default: false
    }
  },


  async mounted() {

    if (!this.mostrar) { // Si no se deben mostrar las publicaciones ordenadas, obtener nuevas publicaciones
      this.fetchPublicaciones();

    }


  },
  methods: {


    async fetchPublicaciones() {

      try {
        // Obtener todas las publicaciones
        const publicaciones = await obtenerPublicaciones();

        // Iterar sobre cada publicación para obtener y almacenar sus comentarios
        for (const publicacion of publicaciones) {
          const comentarios = await obtenerComentarios(publicacion.id);
          const interaccion = await obtenerInteraccion(publicacion.id);
          const usuarios = await obtenerUsuarioPorID(publicacion.user);
          // Agregar la publicación junto con sus comentarios al arreglo
          this.publicaciones.push({
            publicacion: publicacion,
            comentarios: comentarios,
            interaccion: interaccion,
            usuarios: usuarios


          });
        }

        console.log("Publicaciones con comentarios:", this.publicaciones);
      } catch (error) {
        console.error("Error al obtener publicaciones con comentarios:", error.message);
      }

    },


    capitalizarPrimeraPalabra,

    darMeGusta() {

    },
    darNoMeGusta() {

    },

    agregarComentario() {

    },

    contarLikes(interaccion) {
      if (!interaccion) return 0;
      return interaccion.filter(item => item.typeInteraction).length;
    },
    contarDislikes(interaccion) {
      if (!interaccion) return 0;
      return interaccion.filter(item => !item.typeInteraction).length;
    },
    agregarFondoCategoria(categoria) {
      //cambiar color del bg de span publicacion-category dependiendo de su valor por ejemplo si es Noticias = el bg sera rojo
      return colorDictionary[categoria]
    }


  }
}

</script>

<style></style>