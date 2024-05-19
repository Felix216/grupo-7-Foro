<template>

    <header>
        <Navbar class="w-screen"> </Navbar>
    </header>


    <!-- DATOS USUARIO LOGEADO

    <div class="container mx-auto">

        <p>Usuario: {{ nombre }}</p>
        <p>Email: {{ correo }}</p>
        <p>Contraseña: {{ contraseña }}</p>
    </div>
 -->



    <div class=" flex flex-row bg-gray-100 w-screen" >
        <aside-component class=""></aside-component>


        <div class="flex flex-col px-20 w-screen">
            <crear-publicacion class=""></crear-publicacion>
            <articulo-component  ref="ArticuloComponent" ></articulo-component>

        </div>
    </div>









    <footer-component >

    </footer-component>

</template>

<script>
import Navbar from '@/components/NavbarComponent.vue'
import AsideComponent from '@/components/AsideComponente.vue'
import FooterComponent from '@/components/FooterComponent.vue'
import CrearPublicacion from '@/components/CrearPublicacion.vue'
import ArticuloComponent from '@/components/ArticuloComponente.vue'
export default {
    created() {
        const usuario = JSON.parse(localStorage.getItem('usuarioLogeado'));
        if (usuario) {
            this.nombre = usuario.name;
            this.correo = usuario.email;
            this.contraseña = usuario.password;

        }

    },

    components: {
        Navbar,
        AsideComponent,
        FooterComponent,
        ArticuloComponent,
        CrearPublicacion,

    },
    data() {
        return {
            nombre: '',
            correo: '',
            contraseña: '',

        }
    },
    provide(){
        return{
            refrescarPublicaciones: this.refrescarPublicaciones
        }
    },
    methods:{
        async refrescarPublicaciones(){
            //accedo al componente articuloComponent al metodo fetchPublicacion con el metodo $refs
            this.$refs.ArticuloComponent.fetchPublicaciones();
        }
    }
}
</script>

<style></style>