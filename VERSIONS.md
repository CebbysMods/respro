# Resource Provider Mod Version Changes

---

## Version 0.1.1 - Entry of dynamic resources

> *Additions:*
> * API for registering resource pack providers
> * API for registering resource packs
> * Way to generate resource packs dynamically using code.
> * Possibility to generate resource packs with following 
options and API:
>  * Pack name.
>  * Pack id.
>  * Pack description.
>  * Pack icon.
>  * API to add and support any resources.
> * Possibility to generate assets for resource packs. Currently,
implements following asset generation:
>  * Item models.
>  * Block states.
> * Possibility to generate data for resource packs. Currently,
implements following data generation:
>  * [Custre](https://github.com/CebbysMods/custre-mod) recipes.

---

## Version 0.2.0 - The Great Refactor

> *Changes:*
> * Refactored Respro mod making it backwards incompatible
> * Updated API for pack generation to set:
>  * Pack name.
>  * Pack id.
>  * Pack description.
>  * Pack icon.
>  * Pack source.
> * Updated API for assets generation to set:
>  * Variant block states.
>  * Multipart block states.
> * Updated API for data generation to set:
>  * [Custre recipes.](https://www.curseforge.com/minecraft/mc-mods/custre) 

> *Additions:*
> * Added API for pack generation to set:
>  * Pack always enabled or not.
>  * API to add and support any resources.
> * Added API for data generation to set:
>  * [KleeSlabs compatibility resources.](https://www.curseforge.com/minecraft/mc-mods/kleeslabs)

---

## Version 0.2.1 - The Server Fix I

> *Fixes:*
> * Respro fails to start the server environment.

---