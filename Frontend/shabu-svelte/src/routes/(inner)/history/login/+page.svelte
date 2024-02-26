<script lang="ts">
	import { UserRole } from '$lib/model/enum';
	import * as Table from '$lib/components/ui/table';
	import type { PageData } from './$types';
	export let data: PageData;
	const { user } = data;

	type Card = {
		title: string;
		subtitle: string;
		amount: Number;
	};

	const filteredLoginHistories = user.loginHistories;
	filteredLoginHistories.reverse();

	let cards: Card[] = [
		{
			title: 'Login History',
			subtitle: 'Show amount of Login Histories.',
			amount: user.loginHistories.length
		}
	];

	let search: string = '';
</script>

<page-title class="flex flex-row items-center gap-4 py-4 px-16 h-20 text-4xl font-bold uppercase">
	Login History
</page-title>
<page-header class="flex flex-row gap-4 py-4 px-16 bg-white border-y">
	{#each cards as { title, subtitle, amount }}
		<card class="flex flex-col flex-1 gap-4 justify-between drop-shadow-sm">
			<card-title-box class="flex flex-col gap-1">
				<card-title class="text-2xl font-semibold">{title}</card-title>
				<card-subtitle class="text-md text-slate-400">{subtitle}</card-subtitle>
			</card-title-box>
			<card-amount
				class="flex flex-row gap-4 justify-between items-end text-4xl text-white font-bold truncate p-4 bg-slate-900 drop-shadow rounded"
			>
				<div class="truncate">{amount}</div>
			</card-amount>
		</card>
	{/each}
</page-header>
<page-content class="flex flex-col flex-1 gap-4 py-4 px-16 pb-0 overflow-hidden">
	<div class="border-t overflow-auto">
		<Table.Root>
			<Table.Header>
				<Table.Row>
					<Table.Head class="w-[50px] text-center">#</Table.Head>
					<Table.Head class="text-center w-[160px]">Role</Table.Head>
					<Table.Head>Username</Table.Head>
					<Table.Head class="text-right">Login Date</Table.Head>
				</Table.Row>
			</Table.Header>
			<Table.Body>
				{#each filteredLoginHistories as loginHistory, index}
					<Table.Row>
						<Table.Cell class="font-medium text-center">{index + 1}</Table.Cell>
						<Table.Cell class="text-center capitalize"
							>{user.role === UserRole.ADMIN ? 'Admin' : 'Employee'}</Table.Cell
						>
						<Table.Cell>{user.firstname} {user.lastname}</Table.Cell>
						<Table.Cell class="text-right"
							>{new Date(loginHistory.createDate).toLocaleString()}</Table.Cell
						>
					</Table.Row>
				{/each}
			</Table.Body>
		</Table.Root>
	</div>
</page-content>
