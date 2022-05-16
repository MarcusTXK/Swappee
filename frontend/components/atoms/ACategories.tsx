import { FC } from 'react';
import Image from 'next/image';

interface ACategoriesProps {
  categoryName: string;
  img: StaticImageData;
}

const ACategories: FC<ACategoriesProps> = ({ categoryName, img }) => {
  return (
    <div className="a-category">
      <Image className="a-category__image" height="50px" width="50px" src={img} alt={`${categoryName}`} />
    </div>
  );
};

export default ACategories;
