<template>

  <div>
    <div v-if="mostrar && publicacionesOrdenadas.length > 0">

    <article 
      class="overflow-hidden rounded-lg shadow transition hover:shadow-2xl px-6 my-4 bg-white "
      v-for="(publicacion, index) in publicacionesOrdenadas" :key="index">
      <div class="flex-auto justify-center my-4">
        {{ capitalizarPrimeraPalabra(publicacion.category) }}
      </div>
      <img alt="" :src="publicacion.image" class="h-56 w-full object-cover" />

      <div class="bg-white p-4 sm:p-6">

        <h3 class="mt-0.5 text-lg text-gray-900">{{ publicacion.tittle }}</h3>

        <p class="mt-2 line-clamp-3 text-sm/relaxed text-gray-500">{{ publicacion.content }}</p>

        <div class="flex ">

          <button v-on:click="darMeGusta()" class="flex items-center w-2/12 p-2 rounded-lg">
            <img alt="Me gusta" src="@/assets/MeGusta.png"
              class="mx-2 p-2 w-8 bg-gray-500 hover:bg-blue-600 rounded-lg" />
            <span class="font-semibold text-xl">{{ publicacion.likes }}</span>
          </button>

          <button v-on:click="darNoMeGusta()" class="flex items-center w-2/12 p-2 rounded-lg">
            <img alt="Me gusta" src="@/assets/NoMeGusta.png"
              class="mx-2 p-2 w-8  bg-gray-500 hover:bg-blue-600 rounded-lg" />
            <span class="font-semibold text-xl">{{ publicacion.dislikes }}</span>
          </button>
          <button v-on:click="agregarComentario()" class="flex items-center w-1/4 p-2 rounded-lg">
            <img alt="Me gusta" src="@/assets/Comentario.png"
              class="mx-2 p-2 w-8 bg-gray-500 hover:bg-blue-600  rounded-lg" />
            <span class="font-semibold text-xl">{{ publicacion.comments }}</span>
          </button>


        </div>




        <div>

          <router-link :to="{ name: 'verPublicacion', params: { publicaciones: JSON.stringify(publicacion) } }">Ver
            mas</router-link>
        </div>
      </div>




    </article>
  </div>


    <article v-else class="overflow-hidden rounded-lg shadow transition hover:shadow-2xl px-6 my-4  bg-white "
      v-for="(publicacion, index) in publicaciones" :key="index">
      <div class="flex-auto justify-center my-4">
        {{ capitalizarPrimeraPalabra(publicacion.category) }}
      </div>
      <img alt="" :src="publicacion.image" class="h-56 w-full object-cover" />

      <div class="bg-white p-4 sm:p-6">

        <h3 class="mt-0.5 text-lg text-gray-900">{{ publicacion.tittle }}</h3>

        <p class="mt-2 line-clamp-3 text-sm/relaxed text-gray-500">{{ publicacion.content }}</p>

        <div class="flex ">

          <button v-on:click="darMeGusta()" class="flex items-center w-2/12 p-2 rounded-lg">
            <img alt="Me gusta" src="@/assets/MeGusta.png"
              class="mx-2 p-2 w-8 bg-gray-500 hover:bg-blue-600 rounded-lg" />
            <span class="font-semibold text-xl">{{ publicacion.likes }}</span>
          </button>

          <button v-on:click="darNoMeGusta()" class="flex items-center w-2/12 p-2 rounded-lg">
            <img alt="Me gusta" src="@/assets/NoMeGusta.png"
              class="mx-2 p-2 w-8  bg-gray-500 hover:bg-blue-600 rounded-lg" />
            <span class="font-semibold text-xl">{{ publicacion.dislikes }}</span>
          </button>
          <button v-on:click="agregarComentario()" class="flex items-center w-1/4 p-2 rounded-lg">
            <img alt="Me gusta" src="@/assets/Comentario.png"
              class="mx-2 p-2 w-8 bg-gray-500 hover:bg-blue-600  rounded-lg" />
            <span class="font-semibold text-xl">{{ publicacion.comments }}</span>
          </button>


        </div>




        <div>

          <router-link :to="{ name: 'verPublicacion', params: { publicaciones: JSON.stringify(publicacion) } }">Ver
            mas</router-link>
        </div>
      </div>




    </article>

  </div>







</template>

<script>
import { obtenerPublicaciones } from '@/services/foroService';

export default {
  data() {
    return {
      publicaciones: [''],
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
  },



  async mounted() {

    //this.fetchPublicaciones();

    if (!this.mostrar) { // Si no se deben mostrar las publicaciones ordenadas, obtener nuevas publicaciones
      this.fetchPublicaciones();
    }

  },
  methods: {
    async fetchPublicaciones() {

      this.publicaciones = await obtenerPublicaciones();
    },

    capitalizarPrimeraPalabra(string) {
      if (!string) return '';
      return string.charAt(0).toUpperCase() + string.slice(1);
    },




    darMeGusta() {

    },
    darNoMeGusta() {

    },
    agregarComentario() {

    },

  }
}

</script>

<style></style>