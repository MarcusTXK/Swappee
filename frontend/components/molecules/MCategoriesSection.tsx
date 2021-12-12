import { Box } from '@material-ui/core';
import ACategories from '../atoms/ACategories';
import img from 'public/passportphoto.jpg';

const categories = ["cars", "books", "watches", "electronics", "home appliances", "miscellaneous"]

const MCategoriesSection = () => {
    return (
        <div>
            <Box className = 'm-categories-section'>
                Explore Categories
                <div className = 'm-categories-section__categories'>
                {categories.map(value => 
                    <ACategories categoryName = {value} img = {img}/>
                    )}
                </div>
            </Box>
        </div>
    )
}

export default MCategoriesSection;