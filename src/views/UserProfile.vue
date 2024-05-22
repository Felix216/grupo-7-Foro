<template>
    <NavbarComponent/>
    <div class="bg-slate-200 flex">
        <!-- SIDEBAR -->
        <div class="bg-white m-8 py-8 px-7 rounded-2xl shadow-lg flex flex-col justify-evenly fixed left-0">
            <a href="#" class="texto flex hover:bg-slate-100 rounded-full p-1">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6">
                    <path d="M11.47 3.841a.75.75 0 0 1 1.06 0l8.69 8.69a.75.75 0 1 0 1.06-1.061l-8.689-8.69a2.25 2.25 0 0 0-3.182 0l-8.69 8.69a.75.75 0 1 0 1.061 1.06l8.69-8.689Z" />
                    <path d="m12 5.432 8.159 8.159c.03.03.06.058.091.086v6.198c0 1.035-.84 1.875-1.875 1.875H15a.75.75 0 0 1-.75-.75v-4.5a.75.75 0 0 0-.75-.75h-3a.75.75 0 0 0-.75.75V21a.75.75 0 0 1-.75.75H5.625a1.875 1.875 0 0 1-1.875-1.875v-6.198a2.29 2.29 0 0 0 .091-.086L12 5.432Z" />
                </svg>
                <span class="ml-2">Inicio</span>
            </a>
            <a href="#" class="texto flex hover:bg-slate-100 rounded-full p-1">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6">
                    <path fill-rule="evenodd" d="M18.685 19.097A9.723 9.723 0 0 0 21.75 12c0-5.385-4.365-9.75-9.75-9.75S2.25 6.615 2.25 12a9.723 9.723 0 0 0 3.065 7.097A9.716 9.716 0 0 0 12 21.75a9.716 9.716 0 0 0 6.685-2.653Zm-12.54-1.285A7.486 7.486 0 0 1 12 15a7.486 7.486 0 0 1 5.855 2.812A8.224 8.224 0 0 1 12 20.25a8.224 8.224 0 0 1-5.855-2.438ZM15.75 9a3.75 3.75 0 1 1-7.5 0 3.75 3.75 0 0 1 7.5 0Z" clip-rule="evenodd" />
                </svg>
                <span class="ml-2">Perfil</span>
            </a>
            <a href="#" class="texto flex mb-0 hover:bg-slate-100 rounded-full p-1">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6">
                    <path fill-rule="evenodd" d="M7.5 3.75A1.5 1.5 0 0 0 6 5.25v13.5a1.5 1.5 0 0 0 1.5 1.5h6a1.5 1.5 0 0 0 1.5-1.5V15a.75.75 0 0 1 1.5 0v3.75a3 3 0 0 1-3 3h-6a3 3 0 0 1-3-3V5.25a3 3 0 0 1 3-3h6a3 3 0 0 1 3 3V9A.75.75 0 0 1 15 9V5.25a1.5 1.5 0 0 0-1.5-1.5h-6Zm5.03 4.72a.75.75 0 0 1 0 1.06l-1.72 1.72h10.94a.75.75 0 0 1 0 1.5H10.81l1.72 1.72a.75.75 0 1 1-1.06 1.06l-3-3a.75.75 0 0 1 0-1.06l3-3a.75.75 0 0 1 1.06 0Z" clip-rule="evenodd" />
                </svg>
                <span class="ml-2">Cerrar Sesión</span>
            </a>
        </div>
        <!-- CONTAINER PRINCIPAL -->
        <div class="flex flex-col relative bg-white my-8 ml-64 mr-8 rounded-2xl shadow-lg max-w-2xl">
            <img :src="user.profileBanner" alt="profile-banner" class="rounded-tl-2xl rounded-tr-2xl w-full h-44 object-cover">
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
                    <button @click="mostrarVentanaEditar()" class="bg-blue-600 text-white font-inter rounded-full ml-64 px-4 py-2 hover:bg-blue-500">Editar perfil</button>
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
        <EditProfile v-show="mostrarEditar" @close="mostrarVentanaEditar()" @guardadoExitoso="actualizarLocalStorage" :user="user"/>
    </div>
</template>

<script>
    import axios from 'axios';
    import EditProfile from '../components/EditProfile.vue';
    import PublicationComponent from '../components/PublicationComponent.vue';
    import NavbarComponent from '../components/NavbarComponent.vue';
    export default {
        components: {
            EditProfile,
            PublicationComponent,
            NavbarComponent
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

            let userId = this.$route.params.id;
            this.fetchUser(userId);
        },
        methods: {
            async fetchUser(id) {
                try {
                    let result = await axios.get(`http://localhost:3000/users/${id}`);
                    this.user = result.data;
                } catch (error) {
                    console.error(error);
                }
            },
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
        @apply font-bold font-inter mb-4;
    }
</style>