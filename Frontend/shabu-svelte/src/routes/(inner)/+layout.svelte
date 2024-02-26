<script lang="ts">
	import { UserRole } from '$lib/model/enum';
	import {
		HistoryIcon,
		LayoutDashboardIcon,
		LogOutIcon,
		PencilRulerIcon,
		RockingChairIcon,
		SoupIcon,
		User,
		User2Icon,
		UsersIcon
	} from 'lucide-svelte';
	import * as Avatar from '$lib/components/ui/avatar';
	import { Button } from '$lib/components/ui/button';
	import { page } from '$app/stores';
	import Separator from '$lib/components/ui/separator/separator.svelte';
	import type { LayoutData } from './$types';

	export let data: LayoutData;
	const { user } = data;
</script>

<container class="flex flex-row w-screen h-screen overflow-hidden">
	<aside class="flex flex-col w-80 border-r bg-white drop-shadow z-10">
		<a
			href="/manage"
			class="flex flex-row items-center gap-4 font-bold p-4 hover:bg-slate-200 transition-all"
		>
			<img src="/Logo.svg" alt="Shabu Logo" class="px-4 mr-2" />
		</a>
		<div
			class="relative scale flex flex-row items-center gap-4 p-4 bg-slate-900 transition-all text-white drop-shadow before:absolute before:left-full before:top-0 before:rounded-r before:w-2 before:h-full before:bg-slate-900 before:transition-all"
		>
			<Avatar.Root>
				<Avatar.Image src="/unknow-profile.svg" alt="@shadcn" />
				<Avatar.Fallback>CN</Avatar.Fallback>
			</Avatar.Root>
			<div class="flex flex-col overflow-hidden">
				<div class="flex flex-row items-center gap-2">
					<User2Icon class="w-4 h-4" />
					<div class="truncate">
						{user.firstname}
						{user.lastname}
					</div>
				</div>
				<div
					class="flex flex-row items-center gap-2 text-xs uppercase font-semibold text-orange-400"
				>
					{user.role === UserRole.ADMIN ? 'Admin' : 'Employee'}
				</div>
			</div>
		</div>
		<menu class="flex flex-col flex-1 gap-4 p-4">
			<div class="flex flex-col gap-1">
				<div class="flex flex-row items-center gap-2 my-2">
					<Separator class="w-2" orientation="horizontal" />
					<div class="flex flex-row gap-2 text-slate-400 font-bold text-xs uppercase">
						<PencilRulerIcon class="w-4 h-4" />
						Manage
					</div>
					<Separator class="flex-1" orientation="horizontal" />
				</div>
				<Button
					href="/manage/table"
					variant={$page.url.pathname === '/manage/table' ? 'default' : 'ghost'}
					class="flex flex-row justify-between items-center gap-4"
				>
					Table
					<RockingChairIcon class="w-4 h-4" />
				</Button>
				{#if user.role !== 1}
					<Button
						href="/manage/course"
						variant={$page.url.pathname === '/manage/course' ? 'default' : 'ghost'}
						class="flex flex-row justify-between items-center gap-4"
					>
						Course
						<SoupIcon class="w-4 h-4" />
					</Button>
					<Button
						href="/manage/user"
						variant={$page.url.pathname === '/manage/user' ? 'default' : 'ghost'}
						class="flex flex-row justify-between items-center gap-4"
					>
						User
						<UsersIcon class="w-4 h-4" />
					</Button>
				{/if}
			</div>
			<div class="flex flex-col gap-1">
				<div class="flex flex-row items-center gap-2 my-2">
					<Separator class="w-2" orientation="horizontal" />
					<div class="flex flex-row gap-2 text-slate-400 font-bold text-xs uppercase">
						<HistoryIcon class="w-4 h-4" />
						History
					</div>
					<Separator class="flex-1" orientation="horizontal" />
				</div>
				<Button
					href="/history/payment"
					variant={$page.url.pathname === '/history/payment' ? 'default' : 'ghost'}
					class="flex flex-row justify-between items-center gap-4"
				>
					Payment
				</Button>
				<Button
					href="/history/login"
					variant={$page.url.pathname === '/history/login' ? 'default' : 'ghost'}
					class="flex flex-row justify-between items-center gap-4"
				>
					Login
				</Button>
			</div>
			<div class="flex flex-col gap-1">
				<div class="flex flex-row items-center gap-2 my-2">
					<Separator class="flex-1" orientation="horizontal" />
				</div>
				<Button
					href="/logout"
					variant={$page.url.pathname === '/login' ? 'default' : 'ghost'}
					class="flex flex-row justify-between items-center gap-4"
				>
					Logout
					<LogOutIcon class="w-4 h-4" />
				</Button>
			</div>
		</menu>
	</aside>
	<main class="flex flex-col flex-1">
		<slot />
	</main>
</container>

<style>
</style>
