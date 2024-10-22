<template>
    <li>
        <a :href="href" :title="title">
            <img 
            :src="src" 
            :alt="alt"
            :title="title"
            class="cursor-pointer w-11 h-11 hover:bg-gray-200 p-2 rounded-lg transition duration-500 my-1.5">
        </a>
    </li>
</template>

<script setup>
import { defineProps } from 'vue';

    defineProps({
        src: String,
        alt: String,
        title: String,
        href: String
    })
</script>