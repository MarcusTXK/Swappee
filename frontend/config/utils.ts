export const convertDate = (date:string | undefined) => {
    if (date === undefined) {
        return "";
    }
    return date.split("-").join(" ");
}