<script lang="ts">
	import { PlusIcon, SoupIcon, Trash2Icon } from 'lucide-svelte';
	import * as Tabs from '$lib/components/ui/tabs';
	import Input from '$lib/components/ui/input/input.svelte';
	import * as Table from '$lib/components/ui/table';
	import * as Sheet from '$lib/components/ui/sheet';
	import Label from '$lib/components/ui/label/label.svelte';
	import Button from '$lib/components/ui/button/button.svelte';
	import Textarea from '$lib/components/ui/textarea/textarea.svelte';
	import type { PageData, ActionData } from './$types';
	export let data: PageData;
	export let form: ActionData;
	const { pageData } = data;
	const { courses } = pageData;

	type Card = {
		title: string;
		subtitle: string;
		amount: Number;
	};

	let cards: Card[] = [
		{
			title: `Buffet Course${courses.length ? 's' : ''}`,
			subtitle: `Show amount of Buffet Course${courses.length ? 's' : ''}.`,
			amount: courses.length
		}
	];

	let search: string = '';

	$: filterCourses = courses.filter((e) => new RegExp(search, 'i').test(e.name));
</script>

<page-title class="flex flex-row items-center gap-4 py-4 px-16 h-20 text-4xl font-bold uppercase">
	Manage Course
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
	<div class="flex flex-row gap-2">
		<Input type="text" placeholder="Search by Name" bind:value={search} />
		<Sheet.Root>
			<Sheet.Trigger>
				<Button size="icon">
					<PlusIcon class="w-4 h-4" />
				</Button>
			</Sheet.Trigger>
			<Sheet.Content class="flex flex-col p-0 gap-0 sm:max-w-xl">
				<Sheet.Header class="border-b">
					<Sheet.Title class="flex flex-row p-4">
						<div class="flex flex-row items-center text-4xl font-bold uppercase">Create Course</div>
					</Sheet.Title>
				</Sheet.Header>
				<div class="flex flex-col p-4">
					<form action="?/course" method="post" class="flex flex-col gap-4">
						<div class="flex flex-col gap-2">
							<Label for="createName">Course Name</Label>
							<Input
								id="courseName"
								name="courseName"
								type="text"
								placeholder="Enter the name of course."
								required
							/>
						</div>
						<div class="flex flex-col gap-2">
							<Label for="createName">Course Description</Label>
							<Textarea
								id="courseDescription"
								name="courseDescription"
								placeholder="Enter the description of course."
							/>
						</div>
						<div class="flex flex-col gap-2">
							<Label for="coursePrice">Price</Label>
							<Input
								id="coursePrice"
								name="coursePrice"
								type="number"
								min={1}
								value={1}
								placeholder="Enter the price of course."
								required
							/>
						</div>
						<Button formaction="?/createCourse">Create</Button>
					</form>
				</div>
			</Sheet.Content>
		</Sheet.Root>
	</div>
	<div class="grid grid-cols-1 lg:grid-cols-2 xl:grid-cols-3 gap-2 border-t pt-4 overflow-auto">
		{#each filterCourses as course}
			<Sheet.Root>
				<Sheet.Trigger
					class="flex flex-col bg-white border rounded hover:bg-slate-100 transition-all drop-shadow-sm"
				>
					<div class="flex flex-col p-4 gap-2 w-full">
						<div
							class="flex flex-row justify-between items-center gap-4 w-full font-semibold text-2xl"
						>
							{course.name}
							<div
								class="flex flex-row justify-center items-center w-8 h-8 text-sm border rounded text-slate-400"
							>
								#{course.id}
							</div>
						</div>
						<div class="flex flex-row text-md text-slate-400 flex-1">
							{course.description || ''}
						</div>
					</div>
					<div class="flex flex-row p-4 text-2xl text-orange-400 font-semibold border-t w-full">
						฿ {course.price}
					</div>
				</Sheet.Trigger>
				<Sheet.Content class="flex flex-col p-0 gap-0 sm:max-w-xl">
					<Sheet.Header class="border-b">
						<Sheet.Title class="flex flex-row p-4">
							<div class="flex flex-row items-center text-4xl font-bold uppercase">
								{course.name}
							</div>
						</Sheet.Title>
					</Sheet.Header>
					<div class="flex flex-col p-4 gap-2">
						<form action="?/course" method="post" class="flex flex-col gap-4">
							<Input name="courseId" type="hidden" value={course.id} required />
							<div class="flex flex-col gap-2">
								<Label for="courseName">Course Name</Label>
								<Input
									id="courseName"
									name="courseName"
									type="text"
									value={course.name}
									placeholder="Enter the name of course."
									required
								/>
							</div>
							<div class="flex flex-col gap-2">
								<Label for="createName">Course Description</Label>
								<Textarea
									id="courseDescription"
									name="courseDescription"
									value={course.description}
									placeholder="Enter the description of course."
								/>
							</div>
							<div class="flex flex-col gap-2">
								<Label for="coursePrice">Price</Label>
								<Input
									id="coursePrice"
									name="coursePrice"
									type="number"
									min={1}
									value={course.price}
									placeholder="Enter the price of course."
									required
								/>
							</div>
							<Button formaction="?/updateCourse" class="flex-1">Update</Button>
						</form>
						<form action="?/course" method="post" class="flex flex-row">
							<Input name="courseId" type="hidden" value={course.id} required />
							<Button size="icon" formaction="?/deleteCourse" class="flex-1"
								><Trash2Icon class="w-4 h-4" /></Button
							>
						</form>
					</div>
				</Sheet.Content>
			</Sheet.Root>
		{/each}
	</div>
</page-content>

<style>
</style>
