// Tells TypeScript that .vue files are components so they're exported/imported properly
declare module "*.vue" {
  import { DefineComponent } from "vue";
  const component: DefineComponent<object, object, unknown>;
  export default component;
}
