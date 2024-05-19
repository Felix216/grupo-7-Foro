<template>
  <nav class= " border-b-4 border-b-neutral-400">
    <!-- Si Usuario no posee ningun valor, osea ingresa como por default, le aparezca estas opciones  -->
    <div v-if="!usuario" class="container mx-auto flex justify-end " >
      <router-link to="/" class="">Registrarse</router-link>
      <router-link to="/">Iniciar Sesion</router-link>

      <button  class="border border-x-gray-600 rounded-lg font-bold hover:bg-slate-300 py-2 px-4 mr-6">Registrarse</button>
      <button class="border border-x-gray-600 rounded-lg font-bold hover:bg-slate-300 py-2 px-4 mr-6">Iniciar Sesion</button>
    </div>

    <!-- Si usuario posee algun valor, osea que inicio sesion
     <button class="rounded bg-indigo-600 px-8 py-3 text-sm font-medium text-white transition hover:scale-110 hover:shadow-xl 
                  focus:outline-none focus:ring active:bg-indigo-500">
    -->
    <div v-if="usuario" class="container mx-auto flex justify-end mt-6">
        <router-link to="/" class="rounded-xl text-base font-medium text-white bg-red-700 px-3 py-2 transition hover:scale-110 hover:shadow-2xl hover:bg-red-800 ">Cerrar Sesion</router-link>
        <p>COLOCAR ICONO USUARIO</p>
    </div>


    <!-- Colocar Logo Lado Izquierdo, Y REDIRIGIRLO AL FORO LOGEADO-->

    <div v-if="usuario" class="container mx-auto">
      <p>Logo de la app</p>
      <router-link to="/Foro">Logo APP</router-link>
    </div>

    <!-- Colocar Logo Lado Izquierdo, Y REDIRIGIRLO AL FORO POR DEFAULT (NO LOGEADO)-->
    <div v-if="!usuario" class="container mx-auto">
      <router-link to="/ForoDefault">Logo App</router-link>
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

<style>

</style>