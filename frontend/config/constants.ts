import { grey, green } from '@material-ui/core/colors';

export const COLOURS = {
  PRIMARY_GREEN: green[500],
  LIGHT_GREY: grey[500],
};

export interface Category {
  categoryName: string,
  image: StaticImageData,
}


export const numItemCardsInOnePage = 8;