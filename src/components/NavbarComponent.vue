<template>
  <nav class="bg-white shadow shadow-zinc-500 pb-3">



    <div v-if="!usuario" class="bg-white shadow-lg shadow-slate-400 flex flex-row  justify-between py-5">

      <!-- Colocar Logo Lado Izquierdo, Y REDIRIGIRLO AL FORO POR DEFAULT (NO LOGEADO)-->
      <div class="px-10">
        <router-link to="/ForoDefault">
          <img alt="foro" src="@/assets/ForoLogo.png"
            class="object-contain  bg-black w-2/4 hover:scale-125 hover:shadow-md hover:shadow-black transition duration-300 h-full p-1 rounded-xl" />
        </router-link>
      </div>


      <!-- Si Usuario no posee ningun valor, osea ingresa como por default, le aparezca estas opciones  -->
      <div class="mr-10 flex justify-center items-center ">
        <router-link to="/register" class="rounded-xl text-base font-medium text-white bg-red-700 px-3 py-2 mr-4 transition hover:scale-110 hover:shadow-2xl hover:bg-red-800 ">Registrarse</router-link>
        <router-link to="/login" class="rounded-xl text-base font-medium text-white bg-blue-600 px-3 py-2 mr-4 transition hover:scale-110 hover:shadow-2xl hover:bg-blue-800 ">Iniciar Sesion</router-link>
      </div>
    </div>





    <div v-if="usuario" class="bg-white shadow-lg shadow-slate-400 flex flex-row  justify-between py-5 ">
      <!-- Colocar Logo Lado Izquierdo, Y REDIRIGIRLO AL FORO LOGEADO-->

      <div class="px-10  ">
        <router-link to="/Foro">
          <img alt="foro" src="@/assets/ForoLogo.png"
            class=" object-contain bg-black w-2/4 hover:scale-125 hover:shadow-md hover:shadow-black transition duration-300 h-full p-1 rounded-xl" />
        </router-link>
      </div>

      <!-- Si usuario posee algun valor, osea que inicio sesion-->
      <div class="flex justify-center items-center px-10 ">
        <router-link to="/ForoDefault" @click="cerrarSesionUsuario()"
          class="rounded-xl text-base font-medium text-white bg-red-700 px-3 py-2 mr-2 transition hover:scale-110 hover:shadow-2xl hover:bg-red-800 ">Cerrar
          Sesion</router-link>
      </div>


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

  methods: {
    cerrarSesionUsuario() {
      localStorage.removeItem('usuarioLogeado');

    }
  }
}
</script>

<style></style>