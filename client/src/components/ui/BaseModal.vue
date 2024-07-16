<template>
  <teleport to="body">
  <div @click="$emit('close')"></div>
  <dialog>
    <header>
      <slot name="header"><h2></h2></slot>
    </header>
    <section>
      <slot></slot>
    </section>
    <menu>
      <slot name="actions">
        <button @click="$emit('close')">
          Close
        </button>
      </slot>
    </menu>
  </dialog>
  </teleport>
</template>