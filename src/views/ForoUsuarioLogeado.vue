<template>

    <header class="bg-slate-200  " >
        <Navbar class="bg-white"> </Navbar>
    </header>



    
    <div class=" flex flex-row bg-slate-200 py-4  " >
        <aside-component class = "flex-none w-1/3 " ></aside-component>


        <div class="flex flex-col flex-1 py-5  bg-slate-200 ">
            <crear-publicacion class=""></crear-publicacion>
            <articulo-component class="flex-grow" ref="ArticuloComponent" ></articulo-component>

        </div>

        <noticia-populares ref="NoticiaPopulares"> </noticia-populares>

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
import NoticiaPopulares from '@/components/NoticiaPopulares.vue'

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
        NoticiaPopulares,

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
            this.$refs.NoticiaPopulares.fetchPublicacionesNoticia();
        }
    }
}
</script>

<style></style>