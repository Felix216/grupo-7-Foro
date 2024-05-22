<template>
    <div @click="cerrar()" class="fixed inset-0 flex items-center justify-center bg-gray-900 bg-opacity-50">
        <div class="bg-white rounded shadow p-8 w-1/4" @click.stop>
            <button @click="cerrar()" class="text-gray-500 transition hover:text-gray-600 absolute ml-72">
                <svg class="h-6 w-6" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" />
                </svg>
            </button>
            <h1 class="text-lg font-bold mb-2 ml-2">Editar Perfil</h1>
            <div class="flex flex-col">
                <div class="flex flex-row items-center">
                    <img :src="this.user.profilePicture" alt="banner" class="w-20 h-20 mb-2 ml-2 rounded border border-gray-200">
                    <div class="flex flex-col ml-10 mb-4 mt-4">
                        <label for="subirAvatar" class="bg-blue-600 text-white rounded px-6 py-2 mb-2 hover:bg-blue-500 shadow">Subir Avatar</label>
                        <input id="subirAvatar" type="file" class="hidden"/>
                        <label for="subirAvatar" class="bg-blue-600 text-white rounded px-6 py-2 mb-2 hover:bg-blue-500 shadow">Subir Portada</label>
                        <input  id="subirAvatar" type="file" class="hidden"/>
                    </div>
                </div>
                <label class="ml-2">Nombre de Usuario</label>
                <input v-model="username" type="text" class="border border-gray-400 rounded py-2 px-3 m-2">
                <label class="ml-2">Nombre</label>
                <input v-model="name" type="text" class="border border-gray-400 rounded py-2 px-3 m-2">
                <label class="ml-2">Apellido</label>
                <input v-model="lastname" type="text" class="border border-gray-400 rounded py-2 px-3 m-2">
                <label class="ml-2">Descripcion (opcional)</label>
                <textarea v-model="description" class="border border-gray-400 rounded resize-none m-2 py-2 px-3" rows="4"></textarea>
                <button @click="cerrar(), guardarCambios()" class="rounded bg-blue-600 text-white px-6 py-2 m-2 hover:bg-blue-500">Guardar</button>
            </div>
        </div>
    </div>
</template>

<script>
    import axios from 'axios';
    export default {
        props: ['user'],
        data() {
            return {
                username: this.user.username,
                name: this.user.name,
                lastname: this.user.lastname,
                description: this.user.description
            }
        },
        methods: {
            async guardarCambios() {
                if (!this.validarDatos()) {
                    return;
                }
                try {
                    let result = await axios.patch(
                        `http://localhost:3000/users/${this.user.id}`,
                        {
                            username: this.username,
                            name: this.name,
                            lastname: this.lastname,
                            description: this.description   
                        }
                    );

                    if (result.status==200) {
                        this.$emit('guardadoExitoso');
                    }
                } catch (error) {
                    console.error(error);
                }
            },
            validarDatos() {
                if (!this.username || !this.name || !this.lastname) {
                    alert('Por favor, completa todos los campos obligatorios.');
                    return false;
                }

                if (this.username.length > 20 || this.name.length > 20 || this.lastname.length > 20) {
                    alert('El nombre de usuario, nombre o apellido no deben superar los 20 caracteres.');
                    return false;
                }

                if (this.description.length > 160) {
                    alert('La descripcion no debe superar los 160 caracteres.');
                    return false;
                }

                return true;
            },
            cerrar() {
                this.$emit('close');
            }
        }
    }
</script>

<style>

</style>