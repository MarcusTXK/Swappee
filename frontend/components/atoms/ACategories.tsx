import { FC } from 'react';
import Image from 'next/image';


interface ACategories {
    categoryName: string,
    img: StaticImageData
  }

const ACategories : FC<ACategories> = ({ categoryName, img }) => {
    return (
        <div className = 'a-category'>
            <Image className = 'a-category__image' height = "50px" width = "50px" src = {img}/>
        </div>
    )
}

export default ACategories;