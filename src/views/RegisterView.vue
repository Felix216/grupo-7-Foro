<template>
    <div class="bg-slate-100 h-screen flex justify-center items-center mb-6">
        <div class="bg-white shadow w-80 p-6 rounded">
            <h1 class="font-inter text-center text-xl mb-4">Ingresa tus datos para registrarte.</h1>
            <input type="text" placeholder="Nombre De Usuario" v-model="nombre" class="font-inter border-none w-full text-base mb-4 p-2 bg-slate-50 rounded">
            <input type="email" placeholder="Correo Electronico" v-model="email" class="font-inter border-none w-full text-base mb-4 p-2 bg-slate-50 rounded">
            <input type="password" placeholder="Contraseña" v-model="password" class="font-inter border-none w-full text-base mb-4 p-2 bg-slate-50 rounded">
            <button @click="registrarse" class="font-inter w-full border-none text-base bg-blue-600 rounded text-white p-2 mb-4">Crear Cuenta</button>
            <router-link to="/login">
                <button class="font-inter w-full border-none text-base bg-gray-900 rounded text-white p-2">¿Ya tienes cuenta?</button>
            </router-link>
        </div>
    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        name: 'RegisterView',
        data() {
            return {
                nombre: '',
                email: '',
                password: ''
            }
        },
        methods: {
            async registrarse() {
                let result =  await axios.post("http://localhost:3000/users", {
                    name: this.nombre,
                    email: this.email,
                    password: this.password
                });
                if (result.status==201) {
                    this.$router.push({name:'login'})
                }
            }
        }
    }
</script>