<template>
  <nav class="bg-white shadow shadow-zinc-500 pb-3">
    <!-- Si Usuario no posee ningun valor, osea ingresa como por default, le aparezca estas opciones  -->
    <div v-if="!usuario" class="container mx-auto flex justify-end ">
      <router-link to="/" class="">Registrarse</router-link>
      <router-link to="/">Iniciar Sesion</router-link>

      <button
        class="border border-x-gray-600 rounded-lg font-bold hover:bg-slate-300 py-2 px-4 mr-6">Registrarse</button>
      <button class="border border-x-gray-600 rounded-lg font-bold hover:bg-slate-300 py-2 px-4 mr-6">Iniciar
        Sesion</button>
    </div>


    <div class="bg-white shadow-lg shadow-slate-400 flex flex-row  justify-between py-5 ">
      <!-- Colocar Logo Lado Izquierdo, Y REDIRIGIRLO AL FORO LOGEADO-->

      <div v-if="usuario" class="px-10  ">
        <router-link to="/Foro">
          <img alt="foro" src="@/assets/ForoLogo.png" class="  object-contain  bg-black w-2/4 hover:scale-125 hover:shadow-md hover:shadow-black transition duration-300 h-full p-1 rounded-xl" />

        </router-link>
      </div>

      <!-- Si usuario posee algun valor, osea que inicio sesion-->
      <div v-if="usuario" class="flex justify-center items-center px-10 ">
        <router-link to="/"
          class="rounded-xl text-base font-medium text-white bg-red-700 px-3 py-2 mr-2 transition hover:scale-110 hover:shadow-2xl hover:bg-red-800 ">Cerrar
          Sesion</router-link>
        <p>COLOCAR ICONO USUARIO</p>
      </div>


    </div>

    <!-- Colocar Logo Lado Izquierdo, Y REDIRIGIRLO AL FORO POR DEFAULT (NO LOGEADO)-->
    <div v-if="!usuario" class="container mx-auto">
      <router-link to="/ForoDefault">
        <img alt="foro" src="@/assets/ForoLogo.png" class="absolute inset-0 object-contain  bg-black w-1/4 h-full  rounded-2xl" />

      </router-link>
    </div>



  </nav>


</template>

<script>


export default {
  data() {
    return {
      usuario: '',
    };
  },

  created() {

    const usuario = localStorage.getItem('usuarioLogeado');
    //si usuario contiene algo se add el valor a la variable this.usuario del componente
    if (usuario) {
      this.usuario = JSON.parse(usuario);

    }
  },
}
</script>

<style></style>