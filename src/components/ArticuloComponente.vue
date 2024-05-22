<template>


  <article class="overflow-hidden rounded-lg shadow transition hover:shadow-2xl my-4"
    v-for="(publicacion, index) in publicaciones" :key="index">
    <div class="flex-auto justify-center my-4">
      {{ publicacion.categoria }}
    </div>
    <img alt="" :src="publicacion.imagen" class="h-56 w-full object-cover" />

    <div class="bg-white p-4 sm:p-6">
    
      <h3 class="mt-0.5 text-lg text-gray-900">{{ publicacion.titulo }}</h3>

      <p class="mt-2 line-clamp-3 text-sm/relaxed text-gray-500">{{ publicacion.descripcion }}</p>
      
      <button v-on:click="darMeGusta()">{{ publicacion.meGusta }} </button>
      <button v-on:click="darNoMeGusta()">{{ publicacion.noMeGusta }} </button>
      <button v-on:click="agregarComentario()">{{ publicacion.comentarios }}</button>
      

      <div>

        <router-link :to="{name:'verPublicacion', params:{publicaciones: JSON.stringify(publicacion)} }">Ver mas</router-link>
      </div>
    </div>
 
  </article>


</template>

<script>
import { obtenerPublicaciones } from '@/services/foroService';

export default {
  //debo obtener todas las publicaciones de lo usuarios y mostrarlas
  data() {
    return {
      publicaciones: [''],
    }
  },
 
  async mounted(){
    this.fetchPublicaciones();
  },
  methods:{
    async fetchPublicaciones(){
      this.publicaciones = await obtenerPublicaciones();
      console.log(this.publicaciones);
    },
    
   

    darMeGusta(){

    },
    darNoMeGusta(){

    },
    agregarComentario(){
      
    },

  }
}
</script>

<style></style>