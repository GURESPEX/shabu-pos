<script lang="ts">
	import { LayoutDashboardIcon } from 'lucide-svelte';
	import type { PageData } from './$types';

	export let data: PageData;
	const { pageData } = data;
</script>

<page-title class="flex flex-row items-center gap-4 py-4 px-16 h-20 text-4xl font-bold uppercase">
	<LayoutDashboardIcon class="w-12 h-12 text-white bg-slate-900 p-2 rounded" />
	Dashboard
</page-title>
<page-header>Header</page-header>
<page-content>Content</page-content>

<style>
</style>
