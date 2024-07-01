<template>


  <div
    class="flex flex-col min-h-screen sm:w-2/12 md:w-3/12 lg:w-2/12 xl:w-2/12 justify-between items-center bg-slate-200">

    <div v-if="usuario"
      class="fixed flex flex-col bg-white rounded-xl py-4 px-4 my-4 items-start shadow   lg:w-40  xl:w-48 ">

      <router-link to="/Foro" class="relative my-1">

        <div class="flex justify-center px-2 py-1 text-base text-black text-center hover:bg-gray-300 hover:text-gray-900 hover:font-bold  rounded-lg
          md:px-8  lg:px-5 xl:px-3 xl:text-lg md:text-sm">

          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6 mx-1">
            <path
              d="M11.47 3.841a.75.75 0 0 1 1.06 0l8.69 8.69a.75.75 0 1 0 1.06-1.061l-8.689-8.69a2.25 2.25 0 0 0-3.182 0l-8.69 8.69a.75.75 0 1 0 1.061 1.06l8.69-8.689Z" />
            <path
              d="m12 5.432 8.159 8.159c.03.03.06.058.091.086v6.198c0 1.035-.84 1.875-1.875 1.875H15a.75.75 0 0 1-.75-.75v-4.5a.75.75 0 0 0-.75-.75h-3a.75.75 0 0 0-.75.75V21a.75.75 0 0 1-.75.75H5.625a1.875 1.875 0 0 1-1.875-1.875v-6.198a2.29 2.29 0 0 0 .091-.086L12 5.432Z" />
          </svg>

          <span>Principal</span>
        </div>

      </router-link>
      <router-link to="/Popular" class="relative my-1">
        <div class="flex justify-center px-2 py-1 text-base text-black text-center hover:bg-gray-300 hover:text-gray-900 hover:font-bold  rounded-lg
          md:px-8  lg:px-5 xl:px-3 xl:text-lg md:text-sm">

          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6 mx-1">
            <path fill-rule="evenodd"
              d="M15.22 6.268a.75.75 0 0 1 .968-.431l5.942 2.28a.75.75 0 0 1 .431.97l-2.28 5.94a.75.75 0 1 1-1.4-.537l1.63-4.251-1.086.484a11.2 11.2 0 0 0-5.45 5.173.75.75 0 0 1-1.199.19L9 12.312l-6.22 6.22a.75.75 0 0 1-1.06-1.061l6.75-6.75a.75.75 0 0 1 1.06 0l3.606 3.606a12.695 12.695 0 0 1 5.68-4.974l1.086-.483-4.251-1.632a.75.75 0 0 1-.432-.97Z"
              clip-rule="evenodd" />
          </svg>
          <span class="">Popular</span>
        </div>

      </router-link>

      <botton @click="this.$router.push(`/profile/${usuario.id}`)" class="relative my-1">
        <div class="flex justify-center px-2 py-1 text-base text-black text-center hover:bg-gray-300 hover:text-gray-900 hover:font-bold  rounded-lg
          md:px-8  lg:px-5 xl:px-3 xl:text-lg md:text-sm">

          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6 mx-1">
            <path fill-rule="evenodd"
              d="M18.685 19.097A9.723 9.723 0 0 0 21.75 12c0-5.385-4.365-9.75-9.75-9.75S2.25 6.615 2.25 12a9.723 9.723 0 0 0 3.065 7.097A9.716 9.716 0 0 0 12 21.75a9.716 9.716 0 0 0 6.685-2.653Zm-12.54-1.285A7.486 7.486 0 0 1 12 15a7.486 7.486 0 0 1 5.855 2.812A8.224 8.224 0 0 1 12 20.25a8.224 8.224 0 0 1-5.855-2.438ZM15.75 9a3.75 3.75 0 1 1-7.5 0 3.75 3.75 0 0 1 7.5 0Z"
              clip-rule="evenodd" />
          </svg>

          <span class="">Perfil</span>
        </div>

      </botton>
      <router-link to="/" class="relative my-1" @click=" cerrarSesionUsuario()">
        <div class="flex justify-center px-2 py-1 text-base text-black text-center hover:bg-gray-300 hover:text-gray-900 hover:font-bold  rounded-lg
          xl:text-lg md:text-sm">

          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6 mx-1">
            <path fill-rule="evenodd"
              d="M16.5 3.75a1.5 1.5 0 0 1 1.5 1.5v13.5a1.5 1.5 0 0 1-1.5 1.5h-6a1.5 1.5 0 0 1-1.5-1.5V15a.75.75 0 0 0-1.5 0v3.75a3 3 0 0 0 3 3h6a3 3 0 0 0 3-3V5.25a3 3 0 0 0-3-3h-6a3 3 0 0 0-3 3V9A.75.75 0 1 0 9 9V5.25a1.5 1.5 0 0 1 1.5-1.5h6ZM5.78 8.47a.75.75 0 0 0-1.06 0l-3 3a.75.75 0 0 0 0 1.06l3 3a.75.75 0 0 0 1.06-1.06l-1.72-1.72H15a.75.75 0 0 0 0-1.5H4.06l1.72-1.72a.75.75 0 0 0 0-1.06Z"
              clip-rule="evenodd" />
          </svg>

          <span class="">Cerrar Sesion</span>
        </div>

      </router-link>

    </div>

    <div v-if="!usuario"
      class="fixed flex flex-col bg-white rounded-xl py-4 px-4 my-4 items-start shadow   lg:w-40  xl:w-48 ">

      <router-link to="/ForoDefault" class="relative my-1">

        <div class="flex justify-center px-2 py-1 text-base text-black text-center hover:bg-gray-300 hover:text-gray-900 hover:font-bold  rounded-lg
       md:px-8  lg:px-5 xl:px-3 xl:text-lg md:text-sm">

          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6 mx-1">
            <path
              d="M11.47 3.841a.75.75 0 0 1 1.06 0l8.69 8.69a.75.75 0 1 0 1.06-1.061l-8.689-8.69a2.25 2.25 0 0 0-3.182 0l-8.69 8.69a.75.75 0 1 0 1.061 1.06l8.69-8.689Z" />
            <path
              d="m12 5.432 8.159 8.159c.03.03.06.058.091.086v6.198c0 1.035-.84 1.875-1.875 1.875H15a.75.75 0 0 1-.75-.75v-4.5a.75.75 0 0 0-.75-.75h-3a.75.75 0 0 0-.75.75V21a.75.75 0 0 1-.75.75H5.625a1.875 1.875 0 0 1-1.875-1.875v-6.198a2.29 2.29 0 0 0 .091-.086L12 5.432Z" />
          </svg>

          <span>Principal</span>
        </div>

      </router-link>


      <router-link to="/Popular" class="relative my-1">
        <div class="flex justify-center px-2 py-1 text-base text-black text-center hover:bg-gray-300 hover:text-gray-900 hover:font-bold  rounded-lg
       md:px-8  lg:px-5 xl:px-3 xl:text-lg md:text-sm">

          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6 mx-1">
            <path fill-rule="evenodd"
              d="M15.22 6.268a.75.75 0 0 1 .968-.431l5.942 2.28a.75.75 0 0 1 .431.97l-2.28 5.94a.75.75 0 1 1-1.4-.537l1.63-4.251-1.086.484a11.2 11.2 0 0 0-5.45 5.173.75.75 0 0 1-1.199.19L9 12.312l-6.22 6.22a.75.75 0 0 1-1.06-1.061l6.75-6.75a.75.75 0 0 1 1.06 0l3.606 3.606a12.695 12.695 0 0 1 5.68-4.974l1.086-.483-4.251-1.632a.75.75 0 0 1-.432-.97Z"
              clip-rule="evenodd" />
          </svg>
          <span class="">Popular</span>
        </div>

      </router-link>

      <div class="space-y-2">
        <details class="overflow-hidden rounded border border-gray-300 [&_summary::-webkit-details-marker]:hidden">
          <summary class="flex cursor-pointer items-center justify-between gap-2 bg-white p-4 text-gray-900 transition">
            <span class="text-sm font-medium"> Categorias </span>

            <span class="transition group-open:-rotate-180">
              <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                stroke="currentColor" class="h-4 w-4">
                <path stroke-linecap="round" stroke-linejoin="round" d="M19.5 8.25l-7.5 7.5-7.5-7.5" />
              </svg>
            </span>
          </summary>

          <div class="border-t border-gray-200 bg-white">
            <header class="flex items-center justify-between p-2">
              <router-link to="/ForoDefault" @click="resetCategoria()" type="button"
                class="flex justify-center pl-7 text-sm font-semibold text-gray-900 underline underline-offset-4">
                Reset
              </router-link>
            </header>
            <ul class="space-y-1 border-t border-gray-200 p-4">
              <li v-for="categoria in categorias" :key="categoria">
                <label :for="`Filter${categoria}`" class="inline-flex items-center gap-2">
                  <input type="radio" :id="`Filter${categoria}`" class="size-5 rounded border-gray-300"
                    v-model="categoriaSeleccionada" :value="categoria" />
                  <span class="text-sm font-medium text-gray-700"> {{ categoria }} </span>
                </label>
              </li>
            </ul>

            <router-link
              :to="{ name: 'FiltradoPublicaciones', query: { categoria: categoriaSeleccionada } }">Enviar</router-link>

          </div>
        </details>
      </div>



    </div>

  </div>






</template>

<script>
export default {

  data() {
    return {
      usuario: '',
      categorias: ['Noticia', "Deportes", "Ayuda", "Humor", "Juegos", "Otros"],
      categoriaSeleccionada: null,

    };
  },

  created() {

    const usuario = localStorage.getItem('usuarioLogeado');
    //si usuario contiene algo se add el valor a la variable this.usuario del componente
    if (usuario) {
      this.usuario = JSON.parse(usuario);
    }

  },

  methods: {
    cerrarSesionUsuario() {
      localStorage.removeItem('usuarioLogeado');
    },
    resetCategoria() {
      this.categoriaSeleccionada = '';
    },
  }
}
</script>

<style></style>