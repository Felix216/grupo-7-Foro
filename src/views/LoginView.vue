<template>
    <div class="bg-slate-100 h-screen flex justify-center items-center mb-4">
        <div class="bg-white shadow w-80 p-6 rounded">
            <h1 class="font-inter text-center text-xl mb-4">Inicio de Sesion</h1>
            <input type="email" placeholder="Correo Electronico" v-model="email" class="font-inter border-none w-full text-base mb-4 p-2 bg-slate-50 rounded">
            <input type="password" placeholder="Contraseña" v-model="password" class="font-inter border-none w-full text-base mb-4 p-2 bg-slate-50 rounded">
            <button @click="iniciarSesion" class="font-inter w-full border-none text-base bg-blue-600 rounded text-white p-2 mb-2">Iniciar Sesion</button>
            <router-link to="#" class="font-inter ml-10">¿Olvidaste tu contraseña?</router-link>
            <router-link to="/register">
                <button class="font-inter w-full border-none text-base bg-gray-900 rounded text-white p-2 mt-6">Registrarse</button>
            </router-link>
        </div>
    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        name: 'LoginView',
        data() {
            return {
                email: '',
                password: ''
            }
        },
        methods: {
            async iniciarSesion() {
                if (!this.email || !this.password) {
                    alert('Completa los campos para iniciar sesion!');
                    return;
                }
                
                let result = await axios.get(
                    `http://localhost:3000/users?email=${this.email}&password=${this.password}`
                );
                if (result.status==200 && result.data.length > 0) {
                    localStorage.setItem('usuarioLogeado',JSON.stringify(result.data[0]));
                    alert('Usuario Registrado.');
                } else {
                    alert('Correo electronico o contraseña incorrectos.');
                }
            }
        }
    }
</script>

<style scoped>
    body {
        background-color: rgb(255, 255, 255);
    }
</style>