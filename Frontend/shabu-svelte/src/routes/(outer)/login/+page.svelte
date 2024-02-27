<script lang="ts">
	import { Button } from '$lib/components/ui/button';
	import * as Card from '$lib/components/ui/card';
	import * as Select from '$lib/components/ui/select';
	import { Input } from '$lib/components/ui/input';
	import { Label } from '$lib/components/ui/label';
	import { Checkbox } from '$lib/components/ui/checkbox';
	const frameworks = [
		{
			value: 'sveltekit',
			label: 'SvelteKit'
		},
		{
			value: 'next',
			label: 'Next.js'
		},
		{
			value: 'astro',
			label: 'Astro'
		},
		{
			value: 'nuxt',
			label: 'Nuxt.js'
		}
	];
	let showPassword = false;
</script>

<div class="flex flex-row justify-center items-center h-screen">
	<form action="?/login" method="POST" class="flex flex-col p-6 gap-6 w-96">
		<div class="flex flex-col gap-1">
			<div class="text-2xl font-semibold">Login</div>
			<div class="text-md text-slate-400">Please Login to use this Shabu POS demo.</div>
		</div>
		<div class="flex flex-col gap-2">
			<Label for="userID">User ID</Label>
			<Input id="userID" name="userID" placeholder="Enter your User ID" />
			<Label for="password">Password</Label>
			<Input
				id="password"
				name="password"
				type={showPassword ? 'text' : 'password'}
				placeholder="Enter your Password"
			/>
			<div class="flex items-center space-x-2">
				<Checkbox id="terms" bind:checked={showPassword} />
				<Label for="terms">Show Password</Label>
			</div>
		</div>
		<div class="flex flex-col gap-2">
			<Button>Login</Button>
		</div>
	</form>
</div>

<style>
</style>
