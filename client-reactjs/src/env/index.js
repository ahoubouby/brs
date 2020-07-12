import {
    arg as argDev
} from './dev';
import {
    arg as argProd
} from './pord';

const args = process.env.NODE_ENV === 'prod' ? argProd : argDev;

export {
    args
};