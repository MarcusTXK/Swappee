import { AppState, ItemData } from "./interfaces"

export function selectItemById(state: AppState, itemId: number): ItemData | undefined {
    return state.items.find(item => item.id === itemId);
}