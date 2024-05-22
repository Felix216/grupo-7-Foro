<template>
    <div class="bg-slate-100 flex">
        <!-- SIDEBAR -->
        <div class="bg-white m-8 p-16 h-96 rounded border border-gray-300 flex flex-col justify-evenly fixed left-0">
            <a href="#" class="texto">Home</a>
            <a href="#" class="texto">Settings</a>
            <a href="#" class="texto">Profile</a>
            <a href="#" class="texto">Log out</a>
        </div>
        <!-- CONTAINER PRINCIPAL -->
        <div class="flex flex-col relative bg-white my-8 ml-64 mr-8 rounded border border-gray-300 max-w-2xl">
            <img :src="user.profileBanner" alt="profile-banner" class="rounded-tl rounded-tr w-full h-44 object-cover">
            <div class="absolute top-28 left-16">
                <img :src="user.profilePicture" alt="profile-picture" class="rounded-full w-32 h-32">
            </div>
            <div class="mt-20 mx-12 mb-4">
                <label class="text-2xl font-bold">{{user.username}}</label>
                <br>
                <label class="text-lg">{{user.name}} {{user.lastname}}</label>
                <div class="border border-gray-300 h-20 mt-2 rounded bg-gray-50">
                    <p class="mt-1 break-words">{{user.description}}</p>
                </div>
                <div class="flex-row mt-4">
                    <label class="text-slate-500">{{ posts.length }} publicaciones</label>
                    <label class="mx-4 text-slate-500">0 likes</label>
                    <button @click="mostrarVentanaEditar()" class="bg-blue-500 text-white font-inter rounded-full ml-64 px-4 py-2 hover:bg-blue-400">Editar perfil</button>
                </div>
            </div>
            <div class="border border-gray-300 w-full"></div>
            <div class="m-12 mt-4">
                <h1 class="font-bold font-inter text-2xl mb-4">Tus Publicaciones</h1>
                <p v-show="posts.length==0" class="mt-16 mb-4 ml-48">No tienes publicaciones.</p>
                <ul>
                    <li v-for="post in posts" :key="post.id">
                        <PublicationComponent :post="post"/>
                    </li>
                </ul>
            </div>
        </div>
        <!-- SIDEBAR -->
        <div class="w-1/3 mr-8">
            <div class="bg-white rounded border border-gray-300 m-8 ml-0 px-10 py-6 w-full">
                <h1 class="texto text-xl mb-4">Publicaciones Recientes</h1>
            </div>
        </div>
        <EditProfile v-show="mostrarEditar" @close="mostrarVentanaEditar()" @guardadoExitoso="actualizarLocalStorage" :user="user"/>
    </div>
</template>

<script>
    import axios from 'axios';
    import EditProfile from '../components/EditProfile.vue';
    import PublicationComponent from '../components/PublicationComponent.vue';
    export default {
        components: {
            EditProfile,
            PublicationComponent
        },
        data() {
            return {
                mostrarEditar: false,
                posts: [],
                user: {}
            }
        },
        created() {
            this.obtenerPosts();

            let userJSON = localStorage.getItem("usuarioLogeado");
            this.user = JSON.parse(userJSON);
        },
        methods: {
            mostrarVentanaEditar() {
                this.mostrarEditar = !this.mostrarEditar;
            },
            async obtenerPosts() {
                try {
                    let result = await axios.get('http://localhost:3000/posts');
                    
                    if (result.status == 200) {
                        this.posts = result.data;
                    }
                } catch (error) {
                    console.error(error);
                }
            },
            async actualizarLocalStorage() {
                try {
                    let result = await axios.get(
                        `http://localhost:3000/users/${this.user.id}`
                    );
                    if (result.status==200) {
                        localStorage.setItem("usuarioLogeado", JSON.stringify(result.data));
                        window.location.reload();
                    }
                } catch (error) {
                    console.error(error);
                }
            }
        }
    }
</script>

<style>
    .texto {
        @apply font-bold font-inter;
    }
</style>