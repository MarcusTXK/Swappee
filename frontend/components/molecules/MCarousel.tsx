import 'react-responsive-carousel/lib/styles/carousel.min.css';
import { Carousel } from 'react-responsive-carousel';
import { Box } from '@material-ui/core';
import img from 'public/passportphoto.jpg';
import Image from 'next/image';

const MCarousel = () => {
  return (
    <Box className="m-carousel">
      <Carousel dynamicHeight={true}>
        <div>
          <Image height="700px" src={img} />
          <p className="legend">Legend 1</p>
        </div>
        <div>
          <Image height="700px" src={img} />
          <p className="legend">Legend 2</p>
        </div>
        <div>
          <Image height="700px" src={img} />
          <p className="legend">Legend 3</p>
        </div>
      </Carousel>
    </Box>
  );
};

export default MCarousel;
