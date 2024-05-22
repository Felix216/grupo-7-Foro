<template>
    <UserNotFound v-if="noEncontrado"/>
    <div v-if="!noEncontrado">
        <NavbarComponent/>
        <div class="bg-slate-200 flex">
            <!-- SIDEBAR -->
            <AsideComponente :user="user"/>
            <!-- CONTAINER PRINCIPAL -->
            <div class="flex flex-col relative bg-white my-4 mr-8 rounded-2xl shadow-lg w-1/2">
                <img :src="user.profileBanner" alt="profile-banner" class="rounded-tl-2xl rounded-tr-2xl w-full h-44 object-cover">
                <div class="absolute top-28 left-16">
                    <img :src="user.profilePicture" alt="profile-picture" class="rounded-full w-32 h-32">
                </div>
                <div class="mt-20 mx-12 mb-4">
                    <label class="text-2xl font-bold">{{user.username}}</label>
                    <br>
                    <label class="text-lg">{{user.name}} {{user.lastname}}</label>
                    <div class="border border-gray-300 h-20 mt-2 rounded bg-gray-50">
                        <p class="mx-2 mt-1 break-words">{{user.description}}</p>
                    </div>
                    <div class="flex-row mt-4">
                        <label class="text-slate-500">{{ posts.length }} publicaciones</label>
                        <label class="mx-4 text-slate-500">0 likes</label>
                        <button v-if="isOwnProfile" @click="mostrarVentanaEditar()" class="bg-blue-600 text-white font-inter rounded-full ml-80 px-4 py-2 hover:bg-blue-500">Editar perfil</button>
                    </div>
                </div>
                <div class="border border-gray-300 w-full"></div>
                <div class="m-12 mt-4">
                    <h1 class="font-bold font-inter text-2xl mb-4">Tus Publicaciones</h1>
                    <p v-show="posts.length==0" class="mt-16 mb-4 ml-64">No tienes publicaciones.</p>
                    <ul>
                        <li v-for="post in posts" :key="post.id">
                            <PublicationComponent :post="post" :isOwnProfile="isOwnProfile"/>
                        </li>
                    </ul>
                </div>
            </div>
            <!-- SIDEBAR -->
            <EditProfile v-show="mostrarEditar" @close="mostrarVentanaEditar()" @guardadoExitoso="actualizarLocalStorage" :user="user"/>
        </div>
    </div>
</template>

<script>
    import axios from 'axios';
    import EditProfile from '../components/EditProfile.vue';
    import PublicationComponent from '../components/PublicationComponent.vue';
    import NavbarComponent from '../components/NavbarComponent.vue';
    import UserNotFound from '../components/UserNotFound.vue';
    import AsideComponente from '@/components/AsideComponente.vue';
    export default {
        components: {
            EditProfile,
            PublicationComponent,
            NavbarComponent,
            UserNotFound,
            AsideComponente
        },
        data() {
            return {
                mostrarEditar: false,
                noEncontrado: false,
                posts: [],
                user: {},
                loggedUser: null,
                isOwnProfile: false
            }
        },
        created() {
            this.loggedUser = JSON.parse(localStorage.getItem('usuarioLogeado'));
            let userId = this.$route.params.id;
            this.fetchUser(userId);
            this.obtenerPosts(userId);
        },
        methods: {
            async fetchUser(id) {
                try {
                    let result = await axios.get(`http://localhost:3000/users/${id}`);
                    
                    if (result.status==200) {
                        this.user = result.data;
                        this.isOwnProfile = this.loggedUser && this.loggedUser.id === this.user.id;
                    }
                } catch (error) {
                    console.error(error);
                    this.noEncontrado = true;
                }
            },
            mostrarVentanaEditar() {
                this.mostrarEditar = !this.mostrarEditar;
            },
            async obtenerPosts(id) {
                try {
                    let result = await axios.get(`http://localhost:3000/posts?userId=${id}`);
                    
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
            },
        }
    }
</script>

<style>
    .texto {
        @apply font-bold mb-4;
    }
</style>